require.config({
  paths: {
    'angular': '../lib/angular/angular',
    'angular-animate': '../lib/angular-animate/angular-animate.min',
    'angular-ui-router': '../lib/angular-ui-router/release/angular-ui-router',
    'angular-resource': '../lib/angular-resource/angular-resource.min',
    'angular-ngtable': '../lib/ng-table/dist/ng-table.min',
    'angular-ui-select': '../lib/angular-ui-select/dist/select',
    'angular-sanitize': '../lib/angular-sanitize/angular-sanitize.min',
    'angular-bootstrap': '../lib/angular-bootstrap/ui-bootstrap-tpls.min',
    'toaster': '../lib/angularjs-toaster/toaster',
    'bootstrap': '../lib/bootstrap/dist/js/bootstrap',
    'jquery': '../lib/jquery/dist/jquery.min',
    'metisMenu': '../lib/metisMenu/dist/metisMenu.min',
    'moment': '../lib/moment/min/moment.min',
    'angular-moment': '../lib/angular-moment/angular-moment.min',
    'angular-busy': '../lib/angular-busy/dist/angular-busy.min',

    //The following components are used by reports module
    'jquery-ui': '../lib/jquery-ui/jquery-ui.min',
    'jquery-blockui': '../lib/jquery.blockUI/jquery.blockUI',
    'underscore': '../saiku_components/js/backbone/underscore',
    'json2': '../saiku_components/js/backbone/json2',
    'backbone': '../saiku_components/js/backbone/backbone',
    'ccc-def': '../saiku_components/js/saiku/plugins/CCC_Chart/def',
    'ccc-protovis': '../saiku_components/js/saiku/plugins/CCC_Chart/protovis',
    'ccc-protovis-msie': '../saiku_components/js/saiku/plugins/CCC_Chart/protovis-msie',
    'ccc-pvc': '../saiku_components/js/saiku/plugins/CCC_Chart/pvc',
    'ccc-jquery.tipsy': '../saiku_components/js/saiku/plugins/CCC_Chart/jquery.tipsy',
    'ccc-tipsy': '../saiku_components/js/saiku/plugins/CCC_Chart/tipsy',
    'saiku-renderer': '../saiku_components/js/saiku/render/SaikuRenderer',
    'saiku-table-renderer': '../saiku_components/js/saiku/render/SaikuTableRenderer',
    'saiku-chart-renderer': '../saiku_components/js/saiku/render/SaikuChartRenderer',
    'saiku-embed': '../saiku_components/js/saiku/embed/SaikuEmbed'
  },
  shim: {
    'angular': {
      exports: 'angular',
      deps: ['jquery']
    },
    'angular-resource': {
      deps: ['angular']
    },
    'angular-ui-router': {
      deps: ['angular']
    },
    'angular-animate': {
      deps: ['angular']
    },
    'angular-ngtable': {
      deps: ['angular']
    },
    'angular-ui-select': {
      deps: ['angular']
    },
    'angular-sanitize': {
      deps: ['angular']
    },
    'angular-bootstrap': {
      deps: ['angular']
    },
    'angular-moment': {
      deps: ['angular', 'moment']
    },
    'angular-busy': {
      deps: ['angular']
    },
    'toaster': {
      deps: ['angular']
    },
    'metisMenu': {
      deps: ['jquery']
    },
    'bootstrap': {
      deps: ['jquery']
    }
  }
});

require([
    'angular',
    'angular-resource',
    'angular-animate',
    'angular-ngtable',
    'angular-ui-select',
    'angular-sanitize',
    'angular-bootstrap',
    'toaster',
    'angular-ui-router',
    'jquery',
    'bootstrap',
    'metisMenu',
    'moment',
    'angular-busy',
    'angular-moment',
    'core/core.module.js',
    'admin/admin.module.js',
    'brandmgr/brandmgr.module.js',
    'pos/pos.module.js',
    'brand/brand.module.js',
    'branch/branch.module.js',
    'user/user.module.js',
    'joborder/joborder.module.js',
    'customer/customer.module.js',
    'servicetype/servicetype.module.js',
    'inventory/inventory.module.js',
    'acctspayable/acctspayable.module.js',
    'event/event.module.js',
    'reports/reports.module.js'
  ], function (angular) {
  angular.element().ready(function () {
    angular.bootstrap(document, [
      'ui.router',
      'ngResource',
      'ngAnimate',
      'ngTable',
      'ui.select',
      'ngSanitize',
      'ui.bootstrap',
      'toaster',
      'cgBusy',
      'angularMoment',
      'core.module',
      'admin.module',
      'brandmgr.module',
      'pos.module',
      'brand.module',
      'branch.module',
      'user.module',
      'joborder.module',
      'customer.module',
      'servicetype.module',
      'inventory.module',
      'acctspayable.module',
      'event.module',
      'reports.module'
    ]);
  });
});