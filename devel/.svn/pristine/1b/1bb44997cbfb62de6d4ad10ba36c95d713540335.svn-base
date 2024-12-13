<%--
    Document   : surat_ulasan_teknikal
    Created on : May 26, 2010, 11:23:50 AM
    Author     : nurul.izza
--%>

<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<head>


<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>
<script language="javascript" type="text/javascript"
              src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>

<script language="javascript" type="text/javascript"
              src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>

<script language="javascript" type="text/javascript"
				src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

<script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
     $(document).ready(function() {
         maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

  });

function reset1(){
    <%--alert("reset");--%>
    document.getElementById("namaPenyedia").value ="";
    document.getElementById("noRujukan").value ="";
    document.getElementById("ulasan").value ="";
    document.getElementById("syorJabatan1").value ="";
}

function validation(){
     if($("#syorJabatan1").val() == "0"){
            alert('Sila Pilih " SyorJabatan " .');
            $("#syorJabatan1").focus();
            return true;
    }
         if($("#ulasan").val() == ""){
            alert('Sila masukkan " ulasan " terlebih dahulu.');
            $("#ulasan").focus();
            return true;
    }
    return false;
}

function saveJabatanTeknikal(event, f){
    if(validation()){

      }
      else{
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
      }
    }

</script>

</head>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--<s:form beanclass="etanah.view.stripes.pelupusan.SuratUlasanTeknikalActionBean">--%>
<s:form beanclass="etanah.view.penguatkuasaan.JabatanTeknikal2ActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Penyediaan Laporan
            </legend>
            <table>
                <s:hidden name="mohonRujLuar.tarikhJangkaTerima"/>
                <tr>
                    <td><label>Ulasan Daripada :</label>
                    <td>${actionBean.mohonRujLuar.namaAgensi}
                        <s:hidden name="mohonRujLuar.namaAgensi" />
                        <s:hidden name="mohonRujLuar.idRujukan" /></td>
                    
                </tr>
                <tr>
                    <td><label>Tarikh Penyediaan :</label></td>
                    <td><fmt:formatDate value="${actionBean.mohonRujLuar.tarikhRujukan}" pattern="dd/MM/yyyy"/>
                        <s:hidden name="mohonRujLuar.tarikhRujukan" />
                </tr>
                <tr>
                    <td><label>Tarikh Terima :</label></td>
                    <td>
                        <s:text name="mohonRujLuar.tarikhTerima" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </tr>
                <tr>
                    <td> <label>Nama Penyedia :</label> </td>
                    <td><s:text name="mohonRujLuar.namaPenyedia" size="40" id="namaPenyedia" onkeyup="this.value=this.value.toUpperCase();"/></td>
                </tr>
                <tr>
                    <td><label>Nombor Rujukan / Surat Bilangan :</label>
                    <td><s:text name="mohonRujLuar.noRujukan" size="40" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();" />
                </tr>
            </table>
       </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan Jabatan Teknikal
            </legend>
            <table>
                <tr>
                    <td><label><font color="red">*</font>Syor Jabatan :</label>
                    <td><s:select name="mohonRujLuar.diSokong" id="syorJabatan1">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:option value="1">Boleh Diluluskan</s:option>
                        <s:option value="2">Tidak Diluluskan</s:option>
                        <s:option value="3">Tiada Halangan</s:option>
                        <s:option value="4">Sesuai</s:option>
                        <s:option value="5">Tidak Sesuai</s:option>
                        <s:option value="6">Sokong</s:option>
                        <s:option value="7">Tidak Disokong</s:option>
                        <s:option value="8">Tidak Terima Ulasan</s:option>
                        <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                        </s:select> </td>
                </tr>
                <tr>
                    <td valign="top"><label><font color="red">*</font>Ulasan :</label></td>
                    <td><s:textarea name="mohonRujLuar.ulasan" rows="15" cols="50" id="ulasan"/> </td>
                </tr><tr></tr><tr></tr>
                <tr>
                    <%--<td><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>  </td>--%>
                     <%--<s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>--%>

                     <td></td>
                     <td align="right"><s:button name="simpanJabatanTeknikal" id="save" value="Simpan" class="btn" onclick="saveJabatanTeknikal(this.name, this.form);"/>  </td>
                    <td><s:button name="reset" value="Isi Semula" class="btn" onclick="reset1();" /> </td>
                </tr>
            </table>
       </fieldset>
    </div>

</s:form>