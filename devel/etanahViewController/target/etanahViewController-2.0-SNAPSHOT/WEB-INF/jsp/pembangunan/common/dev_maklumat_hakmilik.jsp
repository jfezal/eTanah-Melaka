<%-- 
    Document   : dev_maklumat_hakmilik
    Created on : Jan 12, 2010, 5:26:21 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready(function() {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {
                window.open("${pageContext.request.contextPath}/pembangunan/maklumat_hakmilik?hakmilikDetail&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

    function TrimZeros(instring) {
        return instring.replace(/^0+/g, '');
    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatHakmilikActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Permohonan ${actionBean.permohonan.kodUrusan.kod}
            </legend>
            <span style="color:red;font-weight:bold; font-size: 8pt">Arahan: Sila Pilih YA atau TIDAK untuk Permohonan Lanjutan Tempoh</span>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                        ${line.hakmilik.lot.nama} ${(actionBean.noLotList[line_rowNum-1])} 


                    </display:column>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline;text-transform:capitalize"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline;text-transform:capitalize"/>
                    <c:if test="${edit}">
                        <%--<display:column title="Maklumat Atas Tanah"><s:textarea name="hakmilikPermohonanList[${line_rowNum - 1}].hakmilik.maklumatAtasTanah" class="normal_text" id="maklumatAtasTanah${line_rowNum - 1}" onchange="javascript:valueChange(this.value)" /></display:column>--%>
                        <display:column title="Maklumat Atas Tanah"><s:textarea name="maklumatAtasTanah${line_rowNum - 1}"  class="normal_text">${line.hakmilik.maklumatAtasTanah}</s:textarea></display:column>

                    </c:if>
                    <c:if test="${!edit}">
                        <display:column title="Maklumat Atas Tanah" property="hakmilik.maklumatAtasTanah" style="vertical-align:baseline;text-transform:none">
                        </display:column>
                    </c:if>
                    <c:if test="${edit}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                            <%--<display:column title="Maklumat Atas Tanah"><s:textarea name="hakmilikPermohonanList[${line_rowNum - 1}].hakmilik.maklumatAtasTanah" class="normal_text" id="maklumatAtasTanah${line_rowNum - 1}" onchange="javascript:valueChange(this.value)" /></display:column>--%>
                            <display:column title="Permohonan Lanjut Tempoh Pajakan"><s:select name="hakmilikPermohonanList[${line_rowNum - 1}].lanjutTempoh" style="width:150px" id="lanjutTempoh">
                                    <s:option value="T">Sila Pilih</s:option>
                                    <s:option value="Y">Ya</s:option>
                                    <s:option value="T">Tidak</s:option>
                                </s:select>  </display:column>
                        </c:if>
                    </c:if>
                    <c:if test="${!edit}">
                        <display:column title="Permohonan Lanjut tempoh pajakan"  style="vertical-align:baseline;text-transform:none">
                            <c:if test="${line.hakmilik.maklumatAtasTanah eq 'Y'}"> Ya</c:if>
                            <c:if test="${line.hakmilik.maklumatAtasTanah eq 'T'}"> T</c:if>

                        </display:column>
                    </c:if>
                </display:table>
            </div>
            <c:if test="${edit}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
        </fieldset>
    </div>

</s:form>