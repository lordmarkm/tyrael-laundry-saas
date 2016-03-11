({
  mainConfigFile : "${basedir}/src/main/webapp/app/main.js",
  baseUrl: '${basedir}/src/main/webapp/app',
  dir: "${basedir}/target/webapp-build",
  optimize: '${js.optimize}',
  optimizeCss: '${css.optimize}',
  removeCombined: true,
  modules: [
            {
              name: "main"
            }
          ]
})

