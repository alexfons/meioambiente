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
            cm.createCache(br.com.homemade.domain.Projeto.class.getName() + ".contratosprojetos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Projeto.class.getName() + ".historicos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Projeto.class.getName() + ".municipios", jcacheConfiguration);
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
            cm.createCache(br.com.homemade.domain.Autorizacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Autorizacao.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Autorizacao.class.getName() + ".docs", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Balanco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Banco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Categoriainversao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Certificadoconformidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Certificadoconformidade.class.getName() + ".informescertificadoirregularidades", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Certificadoconformidade.class.getName() + ".notificacaoscertificadoirregularidades", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Certificadoconformidade.class.getName() + ".ocorrenciascertificadoirregularidades", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Clausulascontratuais.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Coluna.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Coluna.class.getName() + ".opcoes", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contabancaria.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contrato.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contrato.class.getName() + ".aditivocontratos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contrato.class.getName() + ".contratosobras", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contrato.class.getName() + ".empresascontratoes", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contratoagente.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Contratoprojeto.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Coordenada.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Custosconcorrentes.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Desapropriacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Empresacontrato.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Fonte.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Informecertificadoirregularidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Linha.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Natureza.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Notificacaocertificadoirregularidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrenciacertificadoirregularidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Planocontas.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Proposta.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Referencia.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Residente.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Rodovia.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Responsavel.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tipoautorizacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tipocertificadoconformidade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Edital.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Editallote.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Fatura.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Faturacontrato.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Fisico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Fisico.class.getName() + ".obraatividades", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Fisicografico.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Medicao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Obraatividade.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Referenciacontrato.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Funcionario.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Informe.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Informe.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Informe.class.getName() + ".ocorrenciasinformes", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Lancamentos.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Licenca.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Licenca.class.getName() + ".condicionantes", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Licenca.class.getName() + ".docs", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Licenca.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Listamovimentacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Log.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Manifestacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Movimentacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Movimentacao.class.getName() + ".listamovimentacaos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrencia.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrencia.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrencia.class.getName() + ".historicos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrencia.class.getName() + ".registros", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrenciainforme.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Registro.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Status.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tabela.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tabela.class.getName() + ".campos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tipolicenca.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tipoocorrencia.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Usuario.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Municipio.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Notificacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Notificacao.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Notificacao.class.getName() + ".ocorrenciasnotificacaos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Obrafisicomensal.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Obrafisicomensal.class.getName() + ".atividadeexecutadamensals", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrencianotificacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Ocorrenciapendencias.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Pagfuncionario.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Pendencias.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Pendencias.class.getName() + ".fotos", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Pendencias.class.getName() + ".ocorrenciaspendencias", jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Planoaquisicoes.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Quantitativo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.ResponsavelTipo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Script.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Solicitacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Supre.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tipo.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Tipocontrato.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.homemade.domain.Valoresdesembolso.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
