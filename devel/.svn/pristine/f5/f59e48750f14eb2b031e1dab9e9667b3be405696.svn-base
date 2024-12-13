<%-- 
    Document   : kemasukan_pelan_manual
    Created on : Dec 17, 2014, 4:47:50 PM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function cc(a, f) {
        var form = $(f).formSerialize();
//        alert(a);

        var daerah;
          var url = '${pageContext.request.contextPath}/utiliti/kemasukanPelan?filterMukim&daerah=' + a + '&';
        window.location = url + form;
    }

    function seksyenfilter(a, f) {
        var form = $(f).formSerialize();
//        alert(a);

        var daerah;
          var url = '${pageContext.request.contextPath}/utiliti/kemasukanPelan?filterSeksyen&mukim=' + a + '&';
        window.location = url + form;
    }






</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:form beanclass="etanah.view.utility.KemasukanPelanManualActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
 <p>
                    <label>Jenis Pelan :</label>
                    <s:select name="jenispelan" id="jenispelan">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="B1">B1</s:option>
                        <s:option value="B2">B2</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Daerah :</label>
                    <s:select name="daerah" id="Daerah"  onchange="cc(this.value,this.form);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listKodDaerah}" value="kod" label="nama" />                    
                    </s:select>
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <s:select name="mukim" onchange="seksyenfilter(this.value,this.form);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listBPM}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Seksyen:</label>
                        <s:select name="seksyen">
                            <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listKodSeksyen}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Lot:</label>
                        <s:text id="idPermohonan" name="noLot"></s:text>
                    </p>
                
                    <p><label><font color="red">*</font>Luas:</label>
                        <s:text id="idPermohonan" name="luas"></s:text>
                    </p>
                    <p><label><font color="red">*</font>No Pelan Akui:</label>
                        <s:text id="idPermohonan" name="nopelanakui"></s:text>
                    </p>
                  
                    <p><label><font color="red">*</font>Unit Ukur :</label>
                        <s:text id="idPermohonan" name="unitukur"></s:text>
                    </p>
                    <p><label><font color="red">*</font>Pelan :</label>
                        <s:file name="file"/>
                </p>

                <p align="center"><s:submit name="simpan" value="Simpan" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('carianPelan')" /></p>

            </fieldset>
        </div>

    </div> 
</s:form>
