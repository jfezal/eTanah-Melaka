<%-- 
    Document   : pertanyaan_status_tkr_ganti
    Created on : Jul 7, 2015, 12:02:33 PM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script language="javascript" >
	$(document).ready(function() {
            var list = document.getElementById('list');
            if(list.value > 0){
                $('#manSeksyen').show();
            }
            if(list.value <= 0){
                $('#manSeksyen').hide();
            }
        });
</script>
<script type="text/javascript">
    function cc(a, f) {
        var form = $(f).formSerialize();
        var daerah;
        var url = '${pageContext.request.contextPath}/kiosk/tukarganti?filterMukim&daerah=' + a + '&';
        window.location = url + form;
    }

    function seksyenfilter(a, f) {
        var form = $(f).formSerialize();
        var daerah;
        var url = '${pageContext.request.contextPath}/kiosk/tukarganti?filterSeksyen&mukim=' + a + '&';
        window.location = url + form;
    }
    
    function validateField(){
        var daerah = document.getElementById('daerah');
        var mukim = document.getElementById('mukim');
        var seksyen = document.getElementById('seksyen');
        var jHakmilik = document.getElementById('jHakmilik');
        var noHakmilik = document.getElementById('noHakmilik');
        var list = document.getElementById('list');
        if(daerah.value == ""){
            alert('Sila Pilih Daerah');
            $('#daerah').focus();
            return false;
        }else if(mukim.value == ""){
            alert('Sila Pilih Mukim');
            $('#mukim').focus();
            return false;
        }else if(jHakmilik.value == ""){
            alert('Sila Pilih Jenis Hakmilik');
            $('#jHakmilik').focus();
            return false;
        }else if(noHakmilik.value == ""){
            alert('Sila Masukkan Nombor Hakmilik');
            $('#noHakmilik').focus();
            return false;
        }else if((list.value > 0)&&(seksyen.value == "")){
            alert('Sila Pilih Seksyen');
            $('#seksyen').focus();
            return false;
        }
    }
    
    function validateField1(){
        var idHakmilik = document.getElementById('idHakmilik');
        if(idHakmilik.value == ""){
            alert('Sila Masukkan ID Hakmilik untuk membat carian.');
            $('#idHakmilik').focus();
            return false;
        }
    }
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.utility.PertanyaanStatusTukarGantiActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
            <br><br><br><br><s:text name="actionBean.listKodSeksyen" value="${fn:length(actionBean.listKodSeksyen)}" id="list" style="display:none;"/>
            <div align="center"><h1> SEMAKAN STATUS TUKARGANTI</h1></div>
            <div align='right'>
                <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                    <img  src="${pageContext.request.contextPath}/images/Home.png" style="height: 30px; width: 30px" border="0" title="LAMAN UTAMA"></a>
                <a href="${pageContext.request.contextPath}/kiosk/main" title="LAMAN UTAMA">
                    <font face="arial">LAMAN UTAMA</font></a>
                &nbsp;&nbsp;&nbsp;
            </div>
            <fieldset class="aras1" style="border-color: white; position:relative ; width: 977px">
                <legend style="font-family:Arial; color: white; font-weight: bold;">ID HAKMILIK</legend>
                <p>
                    <label><em style="color: red;">*</em> ID Hakmilik :</label>
                    <s:text id="idHakmilik" style="padding: 0;
                            height: 20px;
                            position: relative;
                            left: 0;
                            outline: none;
                            border: 1px solid #cdcdcd;
                            border-color: rgba(0,0,0,.15);
                            background-color: #ccc;
                            font-size: 17px;" name="idHakmilik"></s:text>&nbsp;<em style="color: red">Contoh : </em><em style="color: black">040101GMM00000215</em>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="carianHakmilik" style="btn" class="btn"  value="Cari" onclick="return validateField1();"/>&nbsp;
                    <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('carianPelan')" />
                </p>
            </fieldset>
    </div> 
    <div class ="subtitle">
        <div class="content">
            <fieldset class="aras1" style="border-color: white; position:relative ; width: 977px">
                <legend style="font-family:Arial; color: white; font-weight: bold;">BUTIRAN MAKLUMAT</legend>
                <p>
                    <label><em style="color: red;">*</em>Daerah :</label>
                    <s:select name="daerah" id="daerah"  onchange="cc(this.value,this.form);" class="select-style">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listKodDaerah}" value="kod" label="nama" />                    
                    </s:select>
                </p>
                <p>
                    <label><em style="color: red;">*</em>Bandar / Pekan / Mukim  :</label>
                    <s:select name="mukim" onchange="seksyenfilter(this.value,this.form);" class="select-style" id="mukim">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listBPM}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p>
                    <label><em style="color: red;" style="display:none;" id="manSeksyen">*</em>Seksyen  :</label>
                    <s:select name="seksyen" class="select-style" id="seksyen">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listKodSeksyen}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p>
                    <label><em style="color: red;">*</em>Jenis Hakmilik :</label>
                    <s:select name="jenishakmilik" class="select-style" id="jHakmilik">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listJenisHakmilik}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p>
                    <label><em style="color: red;">*</em>No Hakmilik :</label>
                    <s:text id="noHakmilik" style="padding: 0;
                            height: 20px;
                            position: relative;
                            left: 0;
                            outline: none;
                            border: 1px solid #cdcdcd;
                            border-color: rgba(0,0,0,.15);
                            background-color: #ccc;
                            font-size: 17px;" name="noHakmilik"></s:text>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="carianHakmilikdetail" style="btn" class="btn"  value="Cari" onclick="return validateField();"/>&nbsp;
                    <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('carianPelan')" />
                </p>
            </fieldset>
        </div>
    </div> 
</s:form>