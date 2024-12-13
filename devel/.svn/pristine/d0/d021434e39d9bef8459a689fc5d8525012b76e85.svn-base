<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kemasukan Resit Manual</font>
            </div>
        </td>
    </tr>
</table></div>
<s:form beanclass="etanah.view.stripes.hasil.KemasukkanResitManualActionBean">
<%--<s:hidden name="akaun.noAkaun"/>
<s:hidden name="hakmilik.idHakmilik"/>
<s:hidden name="${actionBean.jumCaraBayar}"/>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#popup1").click( function(){
            frm = this.form;
            window.open(frm.action + "/showEditPemohon?idPermohonan=" +$("#idP").val()+"&idHakmilik="+$("#idH").val(), "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500");
        });

        $('.empty').remove();
    });

    function edit(f, id1, id2){
        var queryString = $(f).formSerialize()
        <%--window.open("${pageContext.request.contextPath}/kutipanHasil?cetak&"+queryString, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
        --%>window.open("${pageContext.request.contextPath}/hasil/resit_manual?cetak&"+queryString+"&idHakimilik="+id1+"&idKew="+id2, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function cetak(f, id2){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/resit_manual?cetakBelakangResit&"+queryString+"&idKew="+id2, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>
<font color="red"><b><s:messages/></b></font>
    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayaran</legend>
            <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.dkbList}" requestURI="kutipanHasil" id="line">
                        <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
                        <display:column property="caraBayaran.kodCaraBayaran.nama" title="Cara Bayaran"/>
                        <display:column title="Bank / Agensi Pembayaran">
                            <c:if test="${line.caraBayaran.bank.nama eq null}">-</c:if>
                            <c:if test="${line.caraBayaran.bank.nama ne null}">${line.caraBayaran.bank.nama}</c:if>
                        </display:column>
                        <display:column title="Cawangan">
                            <c:if test="${line.caraBayaran.bankCawangan eq null}">-</c:if>
                            <c:if test="${line.caraBayaran.bankCawangan ne null}">${line.caraBayaran.bankCawangan}</c:if>
                        </display:column>
                        <display:column title="Nombor Rujukan">
                            <c:if test="${line.caraBayaran.noRujukan eq null}">-</c:if>
                            <c:if test="${line.caraBayaran.noRujukan ne null}">${line.caraBayaran.noRujukan}</c:if>
                        </display:column>
                        <display:column property="caraBayaran.amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                        <display:column title="Cetak Pengesahan Cek/PO/MO" style="text-align:center">
                            <c:if test="${line.caraBayaran.kodCaraBayaran.kod eq 'T'}">
                                <s:button name=" " disabled="true" value="Cetak" class="btn"/>
                            </c:if>
                            <c:if test="${line.caraBayaran.kodCaraBayaran.kod ne 'T'}">
                                <s:button name=" " value="Cetak" class="btn" onclick="cetak(this.form, '${line.caraBayaran.idCaraBayaran}');"/>
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
            </p>
        </fieldset>
    </div>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kutipan</legend>
            <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.transList}" id="line">
                        <display:column title="No." ><div align="center">${line_rowNum}</div></display:column>
                        <display:column title="Jenis Urusan">CUKAI TANAH</display:column>
                        <display:column property="akaunKredit.hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:column property="akaunKredit.noAkaun" title="Nombor Akaun"/>
                        </c:if>
                        <display:column property="akaunKredit.hakmilik.noLot" title="Nombor Lot"/>
                        <display:column property="dokumenKewangan.noRujukanManual" title="Nombor Resit"/>
                        <display:column property="dokumenKewangan.amaunBayaran" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                        <%--<display:column title="Cetak Resit">
                                <s:button name=" " onclick="edit(this.form, '${line.akaunKredit.hakmilik.idHakmilik}','${line.dokumenKewangan.idDokumenKewangan}');" value="Cetak" class="btn"/>
                        </display:column>--%>
                        <display:footer>
                            <c:choose>
                                <c:when test="${actionBean.kodNegeri eq 'melaka'}">
                                    <tr>
                                        <td colspan="6" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                        <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                                        <td class="number"><div align="right"><fmt:formatNumber value="${actionBean.jumCaraBayar}" pattern="0.00" /></div></td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                        <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                                    </tr>
                                    <tr>
                                        <td colspan="5" align="right"><div align="right"><b>Jumlah Yang Dibayar (RM) : </b></div></td>
                                        <td class="number"><div align="right"><fmt:formatNumber value="${actionBean.jumCaraBayar}" pattern="0.00" /></div></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </display:footer>
                    </display:table>
                </div>
            </p>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <%--<s:submit name=" " value=" Cetak Resit " class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>--%>
                <%--<s:submit name=" " value="Cetak Semua" class="btn"/>&nbsp;--%>
                <s:submit name="main" value="Menu Utama" class="btn"/>
            </td>
        </tr>
    </table></div>
</s:form>