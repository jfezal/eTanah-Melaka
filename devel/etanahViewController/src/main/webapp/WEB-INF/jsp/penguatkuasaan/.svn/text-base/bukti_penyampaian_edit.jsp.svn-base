<%-- 
    Document   : bukti_penyampaian
    Created on : Feb 24, 2010, 11:08:17 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
 $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
 function change(value){
        if(value == "Berjaya")
        {
            $('#tarikh').show();

        }
        else
        {
            document.form1.tarikhHantar.value="";
            $('#tarikh').hide();


        }
    }
    function test(f) {
        $(f).clearForm();
    }
    function validateForm(){
if($('#noRujukan').val() == ''){
        alert("Sila Isi No Rujukan");
        return false;
    }
    <%--if($('#catat').val() == ''){
        alert("Sila Isi Laporan Pemantauan");
        return false;
    }--%>
    self.opener.refreshPageBukti();
    self.close();
}

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

 <s:form beanclass="etanah.view.penguatkuasaan.BuktiPenyampaianActionBean" name="form1">
  <s:messages /><div class="subtitle">
      <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426'}">
         <fieldset class="aras1">
            <legend>
                Bukti Penyampaian 
            </legend>
            <div class="content">
                
                     <p>
                        <label>No.Rujukan :</label>
                       <s:text name="notis.noRujukan" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;

                    </p>
                    <p>
                 <label>Status penyampaian :</label>
                <s:select name="kodStatusTerima.kod"  value="${actionBean.notis.kodStatusTerima.kod}"  style="width:139px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>                 
                    <p id="tarikh">
                        <label>Tarikh Penyampaian :</label>

                          <s:text name="notis.tarikhHantar" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhHantar"/>&nbsp;

                    </p>
                   <p>
                    <label>Jenis :</label>
                    ${actionBean.notis.kodNotis.nama}&nbsp;

                </p>
            </div>
                          <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idNotis" value="${actionBean.notis.idNotis}"/>
                    <s:submit name="simpanSingle" id="simpan" value="Simpan" class="btn" onclick="return validateForm()"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
        </fieldset>
        
  </c:if>
 
        <c:if test ="${actionBean.permohonan.kodUrusan.kod eq '426'}">
            <fieldset class="aras1">
            <legend>
                Bukti Penyampaian Saman
            </legend>
            <div class="content">

                    <p>
                        <label>No.Rujukan :</label>
                       <s:text name="noRujukan" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;

                    </p>
                    <p>
                 <label>Status penyampaian :</label>
                <s:select name="kodStatusTerima.kod"  value="${actionBean.notis.kodStatusTerima.kod}"  style="width:139px;" id="status">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>     
                    <p id="tarikh">
                        <label>Tarikh Penyampaian :</label>
                          <s:text name="tarikhHantar" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhHantar"/>&nbsp;

                    </p>
                    <p>
                    <label>Jenis :</label>
                    ${actionBean.notis.kodNotis.nama}&nbsp;

                </p>
            </div>
        </fieldset>

         <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idNotis" value="${actionBean.notis.idNotis}"/>
                    <s:submit name="simpanSingle" id="simpan" value="Simpan" class="btn" onclick="return validateForm()"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
        </c:if>
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>

    </div>
</s:form>
