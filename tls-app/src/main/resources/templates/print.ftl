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
  <div style="display:inline-block; vertical-align:top; width: 25%;">
    <img src="/img/logo.png" style="max-height: 100px;" />
  </div>
  <div style="display:inline-block; font-weight: bold; width: 65%;">
    <div style="font-size: 1.1em;">Gina's Laundry</div>
    <div style="font-size: 1em;">A customer friendly laundry shop</div>
    <div style="font-size: 1em;">Block 13, Lot 6. Aldea Homes, Sibulan</div>
  </div>
  <h4>To view job orders, please visit http://ginaslaundry.com/anon/${joborder.customer.code}</h4>
  <hr>
  <ul style="list-style-type: none; padding-left: 10px;">
    <li><strong style="display: inline-block; width: 100px; margin-right: 10px;">Customer</strong>${joborder.customer.formattedName}<br>
    <li><strong style="display: inline-block; width: 100px; margin-right: 10px;">Date received</strong>${joborder.dateReceived.toString('MMM dd, yyyy h:mm a')}
    <li><strong style="display: inline-block; width: 100px; margin-right: 10px;">Date due</strong>${joborder.dateDue.toString('MMM dd, yyyy')}
    <li><strong style="display: inline-block; width: 100px; margin-right: 10px;">Amount due</strong>₱${(joborder.totalAmount - joborder.totalAmountPaid)?string(",##0.00")}
  </ul>
  <dl>
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
</body>
</html>