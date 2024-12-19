
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    $(document).ready(function() {

    });
    function papar(idKewDok){

        var report =null;

  
            report = 'HSLResitUrusanTanah_MLK.rdf';
//                          var url = "reportName=" + report + "%26report_p_id_hakmilik=" + param.value;

        var url = "reportName="+report+"%26report_p_id_kew_dok="+idKewDok;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        }
</script>

<s:form beanclass="etanah.view.stripes.hasil.GantianCekTakLakuActionBean" id="form1">
    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Gantian Resit Batal</font>
              
                </div>
            </td>
        </tr>
    </table></div>

    <font color="red"><b><s:messages/><s:errors/></b></font>
    <div class="subtitle"><s:text name="kodNegeri" id="negeri" style="display:none;"/>
        <fieldset class="aras1">
            <legend>Maklumat Pembayaran</legend>
            <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listCarabayaran}" requestURI="kutipanHasil" id="line">
                        <display:column title="Bil." ><div align="center">${line_rowNum}</div></display:column>
                        <display:column property="kodCaraBayaran.nama" title="Cara Bayaran"/>
                        <display:column title="Bank / Agensi Pembayaran">
                            <c:if test="${line.bank.nama eq null}">-</c:if>
                            <c:if test="${line.bank.nama ne null}">${line.bank.nama}</c:if>
                        </display:column>
                        <display:column title="Cawangan">
                            <c:if test="${line.bankCawangan eq null}">-</c:if>
                            <c:if test="${line.bankCawangan ne null}">${line.bankCawangan}</c:if>
                        </display:column>
                        <display:column title="Nombor Rujukan">
                            <c:if test="${line.noRujukan eq null}">-</c:if>
                            <c:if test="${line.noRujukan ne null}">${line.noRujukan}</c:if>
                        </display:column>
                        <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                        <display:column title="Cetak Resit" style="text-align:center">
                            <display:table name="${actionBean.listDokumenKewangan}" requestURI="kutipanHasil" id="l">
                                <display:column property="dokumenKewangan.idDokumenKewangan" title="No Resit"/>
                                <display:column title=" "><s:button name="cetak" value="Cetak" class="btn" onclick="papar('${l.dokumenKewangan.idDokumenKewangan}')"/></display:column>
                            </display:table>
                        </display:column>
                    </display:table>
                </div>
            </p>
        </fieldset>
    </div>

    
    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <%--<s:submit name=" " value=" Cetak Resit " class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>--%>
                <%--HIDE JAP
                <s:button name=" " value="Cetak Semua" class="btn" onclick="doCetakViaApplet(${actionBean.rst},'semua');" id="cetakSemua"/>&nbsp;
                <s:button name=" " id="cetakSemuaBil" onclick="cetakBil1(this.form);" value="Cetak Bil" class="btn"/>&nbsp;--%>
                <s:submit name="main" value="Menu Utama" class="btn"/>
            </td>
        </tr>
    </table></div>
   

</s:form>