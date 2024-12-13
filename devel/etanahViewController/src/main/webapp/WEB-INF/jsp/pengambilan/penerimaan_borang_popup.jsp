<%-- 
    Document   : penerimaan_borang_popup
    Created on : Jul 8, 2010, 10:09:37 AM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">


$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});
function save(){
        self.opener.refreshPageTanahRizab();
        self.close();}

function validation() {
        if($("#tajuk").val() == ""){
            alert('Sila pilih " Nama Notis/Borang " terlebih dahulu.');
            $("#tajuk").focus();
            return true;
        }
        if($("#datepicker").val() == ""){
            alert('Sila pilih " Tarikh Notis/Borang " terlebih dahulu.');
            $("#datepicker").focus();
            return true;
        }
                
    }

        function save1(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    <%--self.opener.refreshPagePenerimaanBorang();--%>
                    self.close();
                },'html');
            }
        }
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });



  </script>

<div class="subtitle">

         <s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanNotisBorangActionBean" >
             <fieldset class="aras1">
                 <legend></legend>
                 <br/>
                 <br/>

            <p>
                <label for="tajuk">Nama Notis/Borang :</label>
                <s:text name="tajuk" size="20" id="tajuk"/>
            </p>
            <p>
                <label for="tarikhnotis">Tarikh Notis/Borang :</label>
                <s:text name="tarikhNotis" id="datepicker" class="datepicker" size="12" formatPattern="dd/MM/yyyy"/>
            </p>
             <p>
                <label for="namaPenghantar">Nama Penghantar Notis/Borang :</label>
                <s:text name="namaPenghantar" size="20" id="tajuk"/>
            </p>
            <p>
                <label for="">Status Notis/Borang :</label>
                <s:radio name="kodStatusTerima" value="TM" id="kodstatusTerima1"></s:radio> Berjaya dihantarkan
                <s:radio name="kodStatusTerima" value="XT" id="kodstatusTerima2"></s:radio> Tidak Berjaya dihantarkan

            </p>
            <p>
                <label for="">Cara Penghantaran Notis/Borang :</label>
                <s:select name="kodPenghantaran" id="kodPenghantaran" value="${actionBean.carapenghantaran.kod}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                                </s:select>

            </p>

            <p>
                <label for="tarikhhantar">Tarikh Notis/Borang dihantar :</label>
                <s:text name="tarikhHantar" id="datepicker1" class="datepicker" size="12" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label for="tarikhtampal">Tarikh Notis/Borang ditampal :</label>
                <s:text name="tarikhTampal" id="datepicker2" class="datepicker" size="12" formatPattern="dd/MM/yyyy"/>
            </p>
             <p>
                <label for="tarikhterima">Tarikh Notis/Borang diterima :</label>
                <s:text name="tarikhTerima" id="datepicker3" class="datepicker" size="12" formatPattern="dd/MM/yyyy"/>
             </p>
             <p>
                <label for="catatan">Catatan :</label>
                <s:textarea name="catatanTerima" id="catatanTerima" rows="2" cols="15" onblur="this.value=this.value.toUpperCase();" />
             </p>
            <p>
                 <label for="tindakan">Muat Naik Imej :</label>
                                <c:if test="${actionBean.buktiPenerimaan == null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${actionBean.notis.idNotis}');return false;" title="Muat Naik Dokumen"/> /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${actionBean.notis.idNotis}');return false;" title="Imbas Dokumen"/>
                                </c:if>
                                <c:if test="${actionBean.buktiPenerimaan != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                         onclick="doViewReport('${actionBean.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                </c:if>
                            </p>
            <br/>
            
            
            
            
            
            
            
            <p>

                <label>&nbsp;</label>
                <s:hidden name="idPihak"/>
                <s:hidden name="idNotis"/>
                <s:hidden name="idHakmilik"/>
                <s:button name="simpan" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


         </s:form>
</div>