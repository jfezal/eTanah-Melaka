<%-- 
    Document   : SemakanStatusTukarGanti
    Created on : Jul 12, 2018, 12:52:06 PM
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
/*	.header{}

	.footer {
   left: 0;
   bottom: 0;
   width: 100%;
   height: 100pt;
   background-color: #604B89;
   color: white;
   text-align: left;
   }*/
</style>

</head>
<body>
 <s:messages/>
                <s:errors/>
                <s:form beanclass="com.theta.portal.stripes.portal.StatusTukarGantiActionBean" data-toggle="validator">
                    <s:useActionBean beanclass="com.theta.portal.stripes.util.ListUtilWS" var="list"/>
    <div class="container">
	<div class="row">
		<div class="col-sm-7 col-xs-12">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4><b>SEMAKAN TUKAR GANTI HAKMILIK</b></h4>
					</div>
					<div class="panel-body">
					<form>
                                            <div class="form-group">
                                            <div class="well">
    						<label for="idHakmilik">ID Hakmilik : </label>
                                                <!--<input type="text" class="form-control" id="idHakmilik" placeholder="Masukkan id hakmilik anda">-->
                                                
                                                <s:text name="id" class="form-control" placeholder="Masukkan id hakmilik anda"/>
                                                
                                                <p align="right">
                                                    <!--<button type="submit" class="btn btn-primary mb-2">Cari</button>-->
                                                    
                                                <s:submit name="cari" value="Cari" class="btn btn-primary formnovalidate" formnovalidate="true"/>  &nbsp;&nbsp;&nbsp;
                                                <button type="reset" class="btn btn-primary mb-2">Isi Semula</button></p>
                                            </div>
                                            </div>
                                            <div class="form-group">
                                            <div class="well">
                                                <label for="daerah">Daerah : </label>
                                                
                                                <s:select name="daerah" style="width:300px" class="form-control" data-error="Sila Pilih Daerah" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.listDaerah}" label="value" value="kod"/>
                                                        </s:select>
                                                            
                                                <%--<s:select name="daerah"  class="form-control" data-error="Sila Pilih Jenis Carian" required="true">--%>
                                                                <%--<s:option value="1">01-Melaka Tengah</s:option>--%>
      								<%--<s:option value="2">02-Jasin</s:option>--%>
      								<%--<s:option value="3">03-Alor Gajah</s:option>--%>
                                                        <%--</s:select>--%>
                                                
<!--                                                <select class="form-control" id="daerah">
                                                  <option>01-Melaka Tengah</option>
                                                  <option>02-Jasin</option>
                                                  <option>03-Alor Gajah</option>
                                                </select>-->

                                                <label for="bandar">Bandar/Pekan/Mukim : </label>
                                                <s:select name="bpm" style="width:300px" class="form-control" data-error="Sila Pilih Daerah" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.listBandarPekanMukim}" label="value" value="kod"/>
                                                        </s:select>

                                                <label for="jenisHakMilik">Jenis Hakmilik : </label>
                                               <s:select name="jenisHakmilik" style="width:300px" class="form-control" data-error="Sila Pilih Daerah" required="true">
                                                            <s:option value="">Sila Pilih</s:option>
                                                            <s:options-collection collection="${list.listJenisHakmilik}" label="value" value="kod"/>
                                                        </s:select>
                                                <label for="idHakmilik">No Hakmilik : </label>
                                                <s:text name="noHakmilik" class="form-control" placeholder="Masukkan no hakmilik anda"/>
                                                
                                                <s:submit name="cari2" value="Cari" class="btn btn-primary formnovalidate" formnovalidate="true"/>  &nbsp;&nbsp;&nbsp;
                                                <button type="reset" class="btn btn-primary mb-2">Isi Semula</button></p>
                                            </div>
                                            
					</form>
					</div>
				</div>
			</div>
                        </div>

			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4><b>STATUS TUKAR GANTI HAKMILIK</b></h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive" class="table table-hover">     
                                                    <display:table class="table table-hover table table-striped table-bordered" name="${actionBean.listStatusTukarGanti}" style="width:90;" id="line">
                                                    <display:column title="ID Hakmilik">${line.idHakmilik}</display:column>
                                                    <display:column title="No Lot">${line.noLot}</display:column>
                                                    <display:column title="Daerah">${line.daerah}</display:column>
                                                    <display:column title="Didaftarkan Oleh">${line.diDaftarOleh}</display:column>
                                                    <display:column title="Tarikh">${line.tarikh}</display:column>
                                                    <display:column title="Versi">${line.versi}</display:column>
                                                </display:table>
                                                </div>
					</div>
				</div>
			</div>
                </div>
        </div>
</div>

</s:form>
</body>
</html>
