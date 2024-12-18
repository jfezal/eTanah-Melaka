<%--    
    Document   : speks
    Created on : Jul 22, 2010, 4:21:36 PM
    Author     : amir.muhaimin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<title>Fail SPEKS</title>
<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">SPEKS</font>
                    </div></td></tr>
        </table><br>
    </div><br>
    <script type="text/javascript">
        $(document).ready(function () {
            if (dl == null || dl == '') {
                $("#mt").attr("disabled", true);
            }
        });

        function click_clear() {
            document.formSPEKS.datepicker.value = "";
        }
            function papar(noPenyata){

        var report =null;

  
            report = 'HSL_JURNAL_SPEKS_MLK.rdf';
//                          var url = "reportName=" + report + "%26report_p_id_hakmilik=" + param.value;

        var url = "reportName="+report+"%26report_p_no_journal="+noPenyata;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

    <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?reportName="+report+"&report_p_id_kew_dok="+resit, "eTanah",
    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
        }
    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.ispeks.SemakJournalActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>

                <br />
<!--                <p>
                    <label>Jenis Jurnal:</label>
                    ${actionBean.jenisJournal}
                </p>-->
                <p>
                    <label>Jenis Jurnal:</label>
                    <stripes:select name="jenisJournal" id="_kodDaerah" disabled="true">
                        <stripes:option value="">-- Sila Pilih --</stripes:option>
                        <stripes:options-collection collection="${actionBean.listKodJurnal}" label="jenis" value="id"></stripes:options-collection>
                    </stripes:select>
                </p>
                <c:if test="${actionBean.journalBatal ne null}">
                      <p>
                    <label>No Baucer Jurnal Batal :</label>
                <p>${actionBean.journalBatal.noJournal} <a href="#" onclick="paparDetail('${actionBean.journalBatal.noJournal}')">Papar Detail</a></p> 
                </c:if>
                <p>
                    <label>No Baucer Jurnal :</label>
                    ${actionBean.noJournal}
                </p>
                <p>
                    <label>No Rujukan :</label>
                    ${actionBean.noRujukan}
                </p>
                <p>
                    <label>Tarikh Rujukan :</label>
                    ${actionBean.tarikhRujukan}
                </p>
<p>
                    <label>Perihal :</label>
                    <stripes:textarea name="perihal" readonly="true"/>

                </p>
                <br />
            
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="batal" value="Batal" class="btn"/>
                    <stripes:submit name="simpan" value="Simpan" class="btn"/>
                    <stripes:button id="mt" name="downloadFile" value="Papar Penyata Journal" class="btn" onclick="papar('${actionBean.idJournal}')"/>
                    <stripes:submit name="selesai" value="Selesai" class="btn"/>

                </p>
                <br />
            </fieldset>
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>

                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiJournal}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Vot Dana">
                            ${line.kodVotDana.id} | ${line.kodVotDana.perihal}
                        </display:column>
                        <display:column title="Akaun" > ${line.kodTransaksi.kod}|${line.kodTransaksi.nama}</display:column>
                        <display:column title="Debit" ><c:if test="${line.debit ne null}">RM <fmt:formatNumber value="${line.debit}" pattern="#,##0.00"/></c:if>&nbsp;</display:column>
                        <display:column title="Kredit"><c:if test="${line.kredit ne null}">RM <fmt:formatNumber value="${line.kredit}" pattern="#,##0.00"/></c:if>&nbsp;</display:column>
                        
                    </display:table>
                </p>
            </fieldset>
            <p>
                <label>&nbsp;</label>
                <stripes:hidden name="idJournal"/>
                <stripes:hidden name="idTugasan"/>
                <!-- stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/ -->
                <%--<stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>--%>
            </p>
        </div>
    </stripes:form>
</div>


