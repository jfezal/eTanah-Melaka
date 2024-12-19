<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        var dDate = new Date().getFullYear();
        var min = parseInt(${actionBean.min});
        var max = parseInt(${actionBean.max});
        if(min==0)
            $('#mn').val("");
        if(max==0)
            $('#mx').val("");
        if(min==dDate)
            $('#mn').val("");
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        text-align:right;
        width:13em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        text-align:left;
        float:left;
        width:7em;
    }
</style>

<s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean">

    <fieldset class="aras1">
        <legend>Maklumat Akaun</legend>
        <%-- <div align="right"><a id="close">Close This Window</a>&nbsp;&nbsp;&nbsp;&nbsp;</div>--%>
        <p>
            <label>ID Hakmilik :</label>
            ${actionBean.idHakmilik}&nbsp;
        </p>
        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
            <p>
                <label>Nombor Akaun :</label>
                ${actionBean.account}&nbsp;
            </p>
        </c:if>
        <p>
            <label>Tahun Semasa :</label>
            ${actionBean.tahun}&nbsp;
        </p>
        <c:if test="${actionBean.receipt.idDokumenKewangan != null}">
            <p>
                <label>Resit Semasa :</label>
                ${actionBean.receipt.idDokumenKewangan}&nbsp;
            </p>
            <p>
                <label>Status Resit Semasa :</label>
                ${actionBean.receipt.status.nama}&nbsp;
            </p>
            <p>
                <label>Jumlah Bayaran :</label>RM
                <fmt:formatNumber value="${actionBean.receipt.amaunBayaran}"  pattern="#,###.00"/>&nbsp;
            </p>
            <p>
                <label>Tarikh Bayaran :</label>
                <fmt:formatDate value="${actionBean.receipt.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy HH:mm a"/>&nbsp;
            </p>
        </c:if>
        <%--<c:if test="${actionBean.kodNegeri ne 'melaka'}">--%>
        <p>
            <label>Tahun Tunggakan :</label>
        <table>
            <tr>
                <td><s:text name="min" size="4" readonly="true" id="mn"/></td>
                <td>-</td>
                <td><s:text name="max" size="4" readonly="true" id="mx"/></td>
            </tr>
        </table>
    </p>
    <%--</c:if>--%>
    <div class="content" align="center">
        <table align="center" border="0" class="tablecloth">
            <tr>
                <th width="170">Perkara</th>
                <th width="170">Semasa (RM)</th>
                <th width="170">Tunggakan (RM)</th>
            </tr>
            <tr align="center">
                <th width="170">Cukai Tanah</th>
                <td class="tdLabel">
                    <center><font face="Tahoma" color="black">
                            <fmt:formatNumber  value="${actionBean.hakmilik.cukaiSebenar}" pattern="#,###,##0.00"/>
                        </font></center>
                </td>
                <td class="tdLabel">
                    <center><font face="Tahoma" color="black">
                            <fmt:formatNumber  value="${actionBean.tunggakan}" pattern="#,###,##0.00"/>
                        </font></center>
                </td>
            </tr>
            <tr align="center">
                <th width="170">Denda</th>
                <td class="tdLabel">
                    <center><font face="Tahoma" color="black">
                            <fmt:formatNumber  value="${actionBean.dendaSemasa}" pattern="#,###,##0.00"/>
                        </font></center>
                </td>
                <td class="tdLabel">
                    <center><font face="Tahoma" color="black">
                            <fmt:formatNumber  value="${actionBean.dendaTunggakan}" pattern="#,###,##0.00"/>
                        </font></center>
                </td>
            </tr>
            <tr align="center">
                <th width="170">Remisyen</th>
                <td class="tdLabel">
                    <center><font face="Tahoma" color="black">
                            <fmt:formatNumber  value="${actionBean.remisyen}" pattern="#,###,##0.00"/>
                        </font></center>
                </td>
                <td><center>-</center></td>
            </tr>
            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <tr align="center">
                    <th width="170">Cukai Tali Air</th>
                    <td><center>-</center></td>
                    <td class="tdLabel">
                        <center><font face="Tahoma" color="black">
                                <fmt:formatNumber  value="${actionBean.tunggakanTaliAir}" pattern="#,###,##0.00"/>
                            </font></center>
                    </td>
                </tr>
            </c:if>
            <tr align="center">
                <th width="170">Notis</th>
                <td><center>-</center></td>
                <td class="tdLabel">
                    <center><font face="Tahoma" color="black">
                            <fmt:formatNumber  value="${actionBean.notis}" pattern="#,###,##0.00"/>
                        </font></center>
                </td>
            </tr>
        </table>
        <%--<c:if test="${actionBean.kodNegeri eq 'melaka'}">
            <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                <display:column title="No"><div align="center">${line_rowNum}</div></display:column>
                <display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                <display:column title="Transaksi">
                    ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                </display:column>
                <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                <display:column property="status.nama" title="Status"/>
                <display:column title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}">
                    <c:if test="${line.akaunDebit.noAkaun eq '${actionBean.idHakmilik}'}">
                        ${line.amaun}
                    </c:if>
                    <c:if test="${line.akaunKredit.noAkaun eq 'idHakmilik'}">
                        - ${line.amaun}
                    </c:if>
                </display:column>
                <display:footer>
                    <tr>
                        <td colspan="3" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                        <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCajPop}" pattern="0.00"/></div></td>
                    </tr>
                </display:footer>
            </display:table>
        </c:if>--%>
        <c:if test="${actionBean.kodNegeri eq 'melaka'}">

        </c:if>
        <%--<c:if test="${actionBean.kodNegeri ne 'melaka'}">
            <hr width="60%">
            <table align="center" border="0">
                <tr>
                    <td id="tdLabel">Cukai Tanah :</td>
                    <td id="tdDisplay">&nbsp;
                        <fmt:formatNumber value="${actionBean.hakmilik.cukaiSebenar}" pattern="RM 0.00"/>
                    </td>
                    <td id="tdLabel">Denda :</td>
                    <td id="tdDisplay">&nbsp;
                        <c:if test="${actionBean.denda eq '0'}">-</c:if>
                        <c:if test="${actionBean.denda ne '0'}"><fmt:formatNumber value="${actionBean.denda}" pattern="RM 0.00"/></c:if>
                    </td>
                </tr>
                <tr>
                    <td id="tdLabel">Notis :</td>
                    <td id="tdDisplay">&nbsp;
                        <c:if test="${actionBean.notis eq '0'}">-</c:if>
                        <c:if test="${actionBean.notis ne '0'}"><fmt:formatNumber value="${actionBean.notis}" pattern="RM 0.00"/></c:if>
                    </td>
                    <td id="tdLabel">Tunggakan Cukai Tanah :</td>
                    <td id="tdDisplay">&nbsp;
                        <c:if test="${actionBean.tunggakan eq '0'}">-</c:if>
                        <c:if test="${actionBean.tunggakan ne '0'}"><fmt:formatNumber value="${actionBean.tunggakan}" pattern="RM 0.00"/></c:if>
                    </td>
                </tr>
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <tr>
                    <td id="tdLabel">Cukai Tali Air :</td>
                    <td id="tdDisplay">&nbsp;
                        <c:if test="${actionBean.cukaiTaliAir eq '0'}">-</c:if>
                        <c:if test="${actionBean.cukaiTaliAir ne '0'}"><fmt:formatNumber value="${actionBean.cukaiTaliAir}" pattern="RM 0.00"/></c:if>
                    </td>
                    <td id="tdLabel">Tunggakan Tali Air :</td>
                    <td id="tdDisplay">&nbsp;
                        <c:if test="${actionBean.tunggakanTaliAir eq '0'}">-</c:if>
                        <c:if test="${actionBean.tunggakanTaliAir ne '0'}"><fmt:formatNumber value="${actionBean.tunggakanTaliAir}" pattern="RM 0.00"/></c:if>
                    </td>
                </tr>
                </c:if>
                <tr>
                    <td id="tdLabel">Remisyen :</td>
                    <td id="tdDisplay">&nbsp;
                        <c:if test="${actionBean.remisyen eq '0'}">-</c:if>
                        <c:if test="${actionBean.remisyen ne '0'}"><fmt:formatNumber value="${actionBean.remisyen}" pattern="RM 0.00"/></c:if>
                    </td>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay">&nbsp;</td>
                </tr>
            </table>
                    <hr width="60%">
            <table>
                <tr>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay">&nbsp;</td>
                    <td id="tdLabel">Jumlah Perlu DiBayar :</td>
                    <td id="tdDisplay">&nbsp;
                        <fmt:formatNumber value="${actionBean.jumlahCajPop}" pattern="RM 0.00"/>
                    </td>
                </tr>
            </table>
        </c:if>--%>
        <p></p>
    </div>
    <p>
        <label>Jumlah Perlu DiBayar :</label>
        <fmt:formatNumber value="${actionBean.jumlahCajPop}" pattern="RM 0.00"/>&nbsp;
    </p>
    <c:if test="${actionBean.jumlahCajPop <= 0}">
        <p align="center">
            <font color="red">Perhatian :</font> Cukai Tanah bagi Hakmilik ${actionBean.idHakmilik} bagi tahun ini telah dijelaskan.
        </p>
    </c:if>
    <br>

    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td align="right">
                <s:button name=" " value="Tutup" class="btn" id="close"/>
            </td>
        </tr>
    </table>
</fieldset>

</s:form>
