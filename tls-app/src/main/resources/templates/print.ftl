<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Tyrael Laundry</title>
<link rel="icon" type="image/png" href="<@spring.url '/img/logo.png' />" />
<link rel="stylesheet" href="<@spring.url '/lib/bootstrap/dist/css/bootstrap.min.css' />" />
<link rel="stylesheet" href="<@spring.url '/lib/font-awesome/css/font-awesome.min.css' />" />
<link rel="stylesheet" href="<@spring.url '/css/app.css' />" />
<style>
body {
  background: none;
}
</style>
</head>

<body style="width: 30%;">
  <img src="/img/logo.png" style="max-height: 100px;" />
  <h4 class="page-header" style="margin: 0;">${joborder.customer.formattedName} ${joborder.dateReceived}</h4>
  <dl>
    <dt>Date received
    <dd>${joborder.dateReceived}
    <dt>Date due
    <dd>${joborder.dateDue}
    <dt>Services
    <dd>
      <#list joborder.jobServices as service>
      <div class="mt5 servicepanel-small">
        <div class="panel panel-info">
          <div class="panel-heading">
            <div class="row">
              <img src="/app/images/icons/${service.serviceType.icon}" class="img-thirtytwo"  style="margin-left: 10px;" />
              <div class="col-sm-9 text-right" style="float: right">
                <div class="large">${service.serviceType.label}</div>
                <div><strong>${service.weightInKilos}</strong> Kg @ Php ${service.pricePerKilo}/Kg</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            <span class="pull-left">Php ${(service.weightInKilos * service.pricePerKilo)?string(",##0.00")}</span> 
            <div class="clearfix"></div>
          </div>
        </div>
      </div>
      </#list>
    </dd>
    <dt>Items
    <dd>
      <#list joborder.jobItems as item>
      <span style="margin-right: 10px;">${item.quantity} <img src="/app/images/${item.iconPath}" class="img-sixteen"/></span>
      </#list>
    </dd>
    <dt>Total Amount Due
    <dd>₱${joborder.totalAmount?string(",##0.00")}
    <dt>Total Amount Paid
    <dd>₱${joborder.totalAmountPaid?string(",##0.00")}
</body>
</html>