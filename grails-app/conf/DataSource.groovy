mongodb {
  if (System.getProperty('user.name').toLowerCase() == 'juri') {
      if (System.getenv('COMPUTERNAME') == 'JURI-PC') {
          replicaSet = [ "10.20.1.240:27017"]
      } else {
          replicaSet = [ "lbserver:27017"]
      }
  } else {
    replicaSet = [ "localhost:27017"]
  }
//  host = 'lbserver'
//  port = 27017
  databaseName = 'gorm_test'
}


dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:mem:devDB"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}