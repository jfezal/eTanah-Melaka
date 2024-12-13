<%-- 
    Document   : semakan_status_permohonan
    Created on : Jul 12, 2018, 12:49:44 PM
    Author     : user
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Portal eTanah Melaka</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/
➥3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/
➥respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<style>
	.header{}

	.footer {
   left: 0;
   bottom: 0;
   width: 100%;
   height: 100pt;
   background-color: #604B89;
   color: white;
   text-align: left;
   }
</style>

</head>
<body>
 <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.portal.StatusPermohonanActionBean" data-toggle="validator"> 
    <div class="container">
	<div class="row">
		<div class="col-sm-7 col-xs-12">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4><b>CARIAN PERMOHONAN</b></h4>
					</div>
					<div class="panel-body">
							<div class="form-group">
    							<label for="formCarianStatusSelect">Sila pilih kategori ID untuk carian status</label>
<!--    							<select class="form-control" id="formCarianStatusSelect">
      								<option>ID Perserahan / ID Permohonan</option>
      								<option>No Kad Pengenalan</option>
      								<option>No Tentera</option>
      								<option>No Polis</option>
      								<option>No Syarikat</option>
      								<option>Lain-lain</option>
    							</select>-->
                                                        
                                                        <s:select name="katgCarian"  class="form-control" data-error="Sila Pilih Jenis Carian" required="true">
                                                            <%--<s:option value="">Sila Pilih</s:option>--%>
                                                            <s:option value="a">ID Perserahan / ID Permohonan</s:option>
      								<s:option value="b">No Kad Pengenalan</s:option>
      								<s:option value="c">No Tentera</s:option>
      								<s:option value="d">No Polis</s:option>
      								<s:option value="e">No Syarikat</s:option>
      								<s:option value="f">Lain-lain</s:option>
                                                        </s:select>
  							</div>
  							<div class="form-group">
    							<!--<input type="id" class="form-control" id="formCarianStatusInput" placeholder="Masukkan id anda">-->
                                                        <s:text name="id" class="form-control" placeholder="Masukkan id anda"/>

  							</div>
  							<p align="right">
                                                            <!--<button type="submit" class="btn btn-primary mb-2">Cari</button>-->
                                                            <s:submit name="cari" value="Cari" class="btn btn-primary formnovalidate" formnovalidate="true"/>  &nbsp;&nbsp;&nbsp;
                                                            <button type="reset" class="btn btn-primary mb-2">Isi Semula</button></p>

					</div>
				</div>
			</div>
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4><b>SEMAKAN PERMOHONAN</b></h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive" class="table table-hover">     
                                                    <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listStatusPermohonan}" style="width:90;" requestURI="/saveDocuments" id="line">
                                                    <display:column title="No.">${line_rowNum}</display:column>
                                                    <display:column title="ID Permohonan">${line.idPermohonan}</display:column>
                                                    <display:column title="Status">${line.status}</display:column>
                                                    <display:column title="Kod Urusan">${line.kodUrusan}</display:column>
                                                    <display:column title="Nama Urusan">${line.namaUrusan}</display:column>
                                                </display:table>
                                                    
                                                    
                                                    
<!--                                                    <table class="table">
                                                      <thead>
                                                        <tr>
                                                          <th>Bil</th>
                                                          <th>ID Permohonan</th>
                                                          <th>Status</th>
                                                          <th>Kod Urusan</th>
                                                          <th>Nama Urusan</th>
                                                        </tr>
                                                      </thead>
                                                      <tbody>
                                                        <tr>
                                                          <td>1</td>
                                                          <td>${actionBean.idPermohonan}</td>
                                                          <td>${actionBean.status}</td>
                                                          <td>${actionBean.kodUrusan}</td>
                                                          <td>${actionBean.namaUrusan}</td>
                                                        </tr>
                                                      </tbody>
                                                    </table>-->
                                                    </div>
					</div>
				</div>
			</div>
  		</div>
  		<!--div class="col-xs-5">
  			<div class="well">
  				<h4>Pautan Pantas</h4>
  					<div class="list-group">
    					<a href="#" class="list-group-item"><img src="img/icons/Permohonan.png" width="10%"> Semakan Status Permohonan</a>
    					<a href="#" class="list-group-item"><img src="img/icons/TukarGanti.png" width="10%"> Semakan Status Tukar Ganti</a>
    					<a href="#" class="list-group-item"><img src="img/icons/Semakan.jpg" width="10%"> Senarai Semak Dokumen</a>
    					<a href="#" class="list-group-item"><img src="img/icons/ebayar.png" width="10%"> eBayar</a>
    					<a href="#" class="list-group-item"><img src="img/icons/MuatTurun.jpg" width="10%"> Kutipan Agensi</a>
    					<a href="#" class="list-group-item"><img src="img/icons/MuatTurun.jpg" width="10%"> Jabatan Teknikal</a>
  					</div>
  			</div>
  		</div-->
	</div>
</div>

  </s:form>   
</body>
</html>
