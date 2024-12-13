<%--
    Document   : Borang F
    Created on : 23-Nov-2009, 12:25:52
    Author     : nordiyana
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function validation() {
        var count = $("#count").val();
        for(var i=1;i<=count;i++){
            var tempohKeteranganBertulis = $("#tempohKeteranganBertulis"+(i - 1)).val();
            if(tempohKeteranganBertulis == ""){
                alert('Sila pilih " Tempoh Bertulis " terlebih dahulu.');
                $("#tempohKeteranganBertulis"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanBorang">
   <s:messages/>
<s:errors/>
<div>
    <fieldset class="aras1">
        <legend align="center">
                <b>Sila masukkan maklumat berkaitan diruang yang disediakan.</b>
            </legend>
    </fieldset>
</div>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Pemberitahu Mengkehendaki Keterangan Bertulis
                    </legend>

                    <div class="content" align="center">
               <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/borangpengambilanmodule" id="line">
                   <s:hidden name="hakmilikPermohonan.id"></s:hidden>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Tempoh Bertulis" >
                        <%--<c:out value="${actionBean.test1[line_rowNum - 1]}"/>--%>
                        <s:text size="5" name="tempohKeteranganBertulis[${line_rowNum - 1}]" id="tempohKeteranganBertulis${line_rowNum - 1}"/>&nbsp;Hari
                        <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>
                    </display:column>
                    <%--<c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>&nbsp;--%>
                </display:table>
                        <br>
                            <br>
                            <br>
                            <p>
                            <label>&nbsp;</label>

                             <s:button name="saveborangf" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"  />
                            </p>

                    </div>
                </fieldset>
            </div>
</s:form>
