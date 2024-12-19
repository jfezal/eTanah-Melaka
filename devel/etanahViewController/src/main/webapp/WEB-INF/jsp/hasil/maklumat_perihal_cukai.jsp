<%-- 
    Document   : maklumat_perihal_cukai
    Created on : Mar 15, 2013, 11:54:21 AM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function reloadMakLumatCukai(id){
        doBlockUI();
        var url = '${pageContext.request.contextPath}/hasil/perihalCukai?reloadEdit&idHakmilik=' + id;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
</script>

<div class="subtitle displaytag">
    <br>
    <s:form beanclass="etanah.view.stripes.hasil.MaklumatPerihalCukaiActionBean" id="form">
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

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Perihal Cukai
                </legend>
                <p>
                    <label>Tempoh Tunggakan : </label>
                    <s:text name="tahunTunggakan" disabled="true"/>
                </p>
                <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listTransaksi}"  cellpadding="0" cellspacing="0" id="line" pagesize="10" requestURI="/common/perihalCukai">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh Transaksi"><fmt:formatDate pattern="dd/MM/yyyy H:mm:ss a" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                        <display:column title="Transaksi" >${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                        <display:column title="Nombor Resit">
                            <c:if test="${line.dokumenKewangan.idDokumenKewangan ne null}">${line.dokumenKewangan.idDokumenKewangan}</c:if>
                            <c:if test="${line.dokumenKewangan.idDokumenKewangan eq null}">-</c:if>
                        </display:column>
                        <display:column title="Amaun (RM)">
                            <div align="right"><fmt:formatNumber pattern="#,###,##0.00" value="${line.amaun}"/></div>
                        </display:column>
                        <display:column property="status.nama" title="Status"/>
                        <display:footer>
                            <tr>
                                <td colspan="4"><div align="right">Jumlah (RM):</div></td>
                                <td><div align="right"><fmt:formatNumber pattern="#,###,##0.00" value="${actionBean.akaunBaki.baki}"/></div></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
                </p>
            </fieldset>
            <fieldset class="aras1">
                <legend>Notis 6A</legend>
                <p>
                    <label>Tarikh Disampaikan :</label>
                    <s:text name="tarikhHantar" disabled="true" formatPattern="dd/MM/yyyy" />
                </p>
                <p>
                    <label>Tarikh Didaftarkan :</label>
                    <s:text name="tarikhTerima" disabled="true" formatPattern="dd/MM/yyyy" />
                </p>
            </fieldset>
            <br>
        </div>
    </s:form>
</div>