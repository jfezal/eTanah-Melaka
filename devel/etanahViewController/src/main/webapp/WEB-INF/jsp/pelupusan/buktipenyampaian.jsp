<%-- 
    Document   : mlk_buktipenyampaian
    Created on : May 19, 2010, 6:26:06 PM
    Author     : nurul.izza
    Modified by: Akmal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

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
<s:form beanclass="etanah.view.stripes.pelupusan.BuktiPenyampaianActionBean">
<s:messages/>
<s:errors/>
<div class="content" align="center">
   <fieldset class="aras1">
    <legend>Bukti Penyampaian</legend>
    <table class="tablecloth" >
                <tr>
                        <th>Jenis Notis</th><th>Tarikh Terima</th><th>Catatan</th><th>Status Terima</th>
                    </tr>
                    <tr>
                        <td>
                            ${actionBean.kodDok}
                        </td>
                        <td>
                            <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq 'PBMT' && actionBean.stageId eq 'rekod_akuan_penerimaan'}">
                             <s:text name="tarikhTerima" id="tarikhTerima" class="datepicker" formatPattern="dd/MM/yyyy" size="12" />
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq 'PBMT' && actionBean.stageId eq 'rayuan_bayaran'}">
                                <s:format value="${actionBean.tarikhTerima}" formatPattern="dd/MM/yy"/> 
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq 'PBMT' && actionBean.stageId eq 'rekod_akuan_penerimaan'}">
                            <s:textarea name="catatanPenerimaan" id="catatanPenerimaan" cols="50"  rows="3" class="normal_text"/>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq 'PBMT' && actionBean.stageId eq 'rayuan_bayaran'}">
                            ${actionBean.catatanPenerimaan} 
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq 'PBMT' && actionBean.stageId eq 'rekod_akuan_penerimaan'}">
                            <s:select name="kodStatusTerima">
                                                    <s:option value="">--Sila Pilih--</s:option>
                                                    <s:option   value="TM"> Diterima </s:option>
                                                    <s:option   value="XT"> Tidak Diterima</s:option>
                            </s:select><br></td>
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq 'PBMT' && actionBean.stageId eq 'rayuan_bayaran'}">
                            ${actionBean.kodStatus.nama} 
                            </c:if>
                        </td>
                </tr>
                 
                    
     </table>
      </div>
    <p>
         <label>&nbsp;</label>
         <s:button  name="simpan" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
     </p>
     
</s:form>
     

