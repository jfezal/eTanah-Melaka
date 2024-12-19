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
<script type="text/javascript">

    function refreshPageBukti(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/bukti_penyampaian?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function popup(h){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/bukti_penyampaian?popupedit&idNotis='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");


    }
    function removeSingle(idNotis)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/bukti_penyampaian?deleteSingle&idNotis='
                +idNotis;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
        self.opener.refreshPageBukti();
    }

</script>
 <s:form beanclass="etanah.view.penguatkuasaan.BuktiPenyampaianActionBean" name="form1">
  <s:messages />
  <s:errors />
  <div class="subtitle">
     
         <fieldset class="aras1" id="locate">
            <legend>
                Bukti Penyampaian 
            </legend>
             <div class="content" align="center">
                 <p>
                 <table>
                     <tr><td width="50%"><label> Jenis Notis / Borang : </label></td><td><s:select name="jenis" value="${actionBean.notis.kodNotis.kod}" id="jenis">
                        <s:option value="">Sila Pilih</s:option>
                      <%-- <s:option value="NK">Notis Kosongkan Tanah</s:option>
                       <s:option value="SP">Surat Peringatan</s:option>--%>
                       <s:option value="NC">Kompaun</s:option>
                       <s:option value="SML">Saman</s:option>
                       <s:option value="LLH">Notis Lucuthak</s:option>
                       <s:option value="SJA">Surat Jaminan</s:option>
                         </s:select> <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                     </td></tr>
                 </table>
               
                   <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
               <%-- <div class="content" align="center">--%>
                    <%--name="${actionBean.senaraiAduanPemantauan}" --%><br/>
                    <display:table class="tablecloth" name="${actionBean.senaraiNotis}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                         <display:column title="Jenis" property="kodNotis.nama"></display:column>
                          <display:column title="Jenis" property="kodNotis.kod"></display:column>
                        <display:column title="No Rujukan" property="noRujukan"></display:column>
                        <display:column title="Status Penyampaian" property="kodStatusTerima.nama">
                        <%--<c:if test="${line.tarikhHantar ne null}">Berjaya</c:if><c:if test="${line.tarikhHantar eq null}">Tidak Berjaya</c:if>--%>
                        </display:column>
                        <display:column title="Tarikh Penyampaian" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhHantar}"/></display:column>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idNotis}')"/>
                            </div>
                        </display:column>
                       
                        <display:column title="Hapus">
                            <div align="center">
                                 <c:if test="${line.kodNotis.kod eq 'LLH' || line.kodNotis.kod eq 'NC'|| line.kodNotis.kod eq 'SML'|| line.kodNotis.kod eq 'SJA'}">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiNotis[line_rowNum-1].idNotis}');" />
                            </div></c:if>
                        </display:column>
                        </display:table>
                </div>
                    
         </fieldset>
  </div>
  
</s:form>
