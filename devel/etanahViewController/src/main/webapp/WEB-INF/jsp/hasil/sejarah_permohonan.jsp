<%-- 
    Document   : sejarah_permohonan
    Created on : Jan 21, 2010, 5:10:36 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function reloadMakLumatCukai(id) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/hasil/sejarah?reloadEdit&idHakmilik=' + id;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
</script>

<div class="subtitle">
    <br>
    <s:form beanclass="etanah.view.stripes.hasil.SejarahPermohonanActionBean">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Hakmilik Terlibat</legend>
                <div align="center">          
                    <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">Hakmilik :</font>
                    <s:select name="idHakmilik" onchange="reloadMakLumatCukai(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:450px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </div>
                <br/>
            </fieldset>
            <br/>
        </div>

        <fieldset class="aras1">
            <legend>
                Sejarah Permohonan
            </legend>
            <c:if test="${actionBean.negeri eq 'melaka'}">
                <p>
                    <label>No. Akaun : </label>
                ${actionBean.hakmilik.akaunCukai.noAkaun}&nbsp;
                </p>
            </c:if>
            <p>
                <label>ID Hakmilik : </label>
            ${actionBean.idHakmilik}&nbsp;
            </p>
            <p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiFasaPermohonan}" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Tarikh Permohonan"><fmt:formatDate pattern="dd/MM/yyyy H:mm:ss a" value="${line.permohonan.infoAudit.tarikhMasuk}"/></display:column>
                    <display:column title="No. Permohonan" property="permohonan.idPermohonan" />
                    <display:column title="Jenis Permohonan" property="permohonan.kodUrusan.nama" />
                    <display:column title="Tarikh Keputusan"><fmt:formatDate pattern="dd/MM/yyyy H:mm:ss a" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                    <display:column title="Status Permohonan" property="keputusan.nama"/>
                    <display:column title="Ulasan" property="ulasan"/>
                </display:table>
            </div>
            </p>
        </fieldset>
    </s:form>
</div>