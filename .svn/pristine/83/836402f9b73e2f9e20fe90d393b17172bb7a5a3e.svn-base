dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb3;MVCC=TRUE"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb3;MVCC=TRUE"
        }
    }
    production {
        dataSource {
            dbCreate = "update" //"update"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://janus:3306/cp?autoreconnect=true" //           url = "jdbc:mysql://192.168.1.112:3306/cp?autoreconnect=true"
            username = "root"
            password = "Saf3tyF1rst"
            // For MySQL production scenarios enable the following settings
          pooled = true
          properties {
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
          }
        }
    }
}
