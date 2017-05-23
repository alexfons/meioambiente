package br.com.homemade.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(br.com.homemade.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Aditivocontrato.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Participante.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tipoadministrativo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrenciaapresentacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Foto.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Inspetor.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Fiscal.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Trecho.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Empresa.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Projeto.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Orgaoemissor.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Condicionante.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Documento.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contratoobra.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Historico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Administrativo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Administrativo.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Administrativo.class.getName() + ".docs", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Administrativo.class.getName() + ".participantes", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Obra.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Obra.class.getName() + ".contratosobras", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Obra.class.getName() + ".historicos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.TipoObra.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Apresentacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Apresentacao.class.getName() + ".ocorrenciasapresentacaos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Apresentacao.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Atividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Atividadeexecutadamensal.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Atividadelicenca.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Auc.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Auc.class.getName() + ".condicionantes", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Auc.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Auc.class.getName() + ".docs", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
