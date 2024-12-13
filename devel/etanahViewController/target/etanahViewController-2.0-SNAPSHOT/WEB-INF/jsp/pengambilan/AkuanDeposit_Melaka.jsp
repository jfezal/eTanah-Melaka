<%--
    Document   : AkaunDeposit
    Created on : 16-Feb-2011, 10:26:15
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page contentType="text/html;charset=windows-1252" 
         import="java.util.*"
         import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>--%>
<%--<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<script type="text/javascript">
    $(document).ready(function() {
        $('#showTable').hide();
    });


    function save1(event, f){
        if(validation()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageHakmilik();
                self.close();
            },'html');
        }
    }
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/depositMelaka?reload&idHakmilik=' + val;
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
                $('#showTable').show();
            }
        });

    }

    function reloadEdit (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/depositMelaka?reloadEdit&idHakmilik=' + val;
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
                $('#showTable').show();
            }
        });

    }

    function klikNilaiTanah() {
        var a = $('#noLot').val();
        var b = a*(125/100);
        alert(b);
        $("#jumlahDeposit").val(b);
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.DepositMelakaActionBean">
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Jadual Pembayaran Deposit
            </legend>
            <table align="center" >
                <tr>
                    <td><display:table class="tablecloth" name="${actionBean.senaraiDeposit}" pagesize="50" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/depositMelaka" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <c:set var="a" value="${actionBean.caraBayaranList[line_rowNum-1]}"/>
                                <c:set var="b" value="${actionBean.dokumenKewanganBayaranList[line_rowNum-1]}"/>
                                <c:set var="c" value="${actionBean.transaksiList[line_rowNum-1]}"/>
                            </display:column>
                            <display:column title="ID Hakmilik" value="${line.hakmilikPermohonan.hakmilik.idHakmilik}"/>
                            <display:column title="No Lot" value="${line.hakmilikPermohonan.lot.nama} ${line.hakmilikPermohonan.noLot}"/>
                            <display:column title="Nilaian Tanah (RM)">
                                <s:text name="nilaiT${line_rowNum - 1}" value="${line.tanah}" size="20" id="nilaiTanah" formatPattern="#,##0.00"/>
                            </display:column>
                            <display:column title="Nilaian Bangunan (RM)">
                                <s:text name="nilaiBangunan${line_rowNum - 1}" value="${line.bangunan}" size="20" id="nilaiBangunan" formatPattern="#,##0.00"/>
                            </display:column>
                            <display:column title="Nilaian Pecah Pisah (RM)">
                                <s:text name="nilaiPecah${line_rowNum - 1}" value="${line.pecahPisah}" size="20" id="nilaiPecah" formatPattern="#,##0.00"/>
                            </display:column>
                            <display:column title="Nilaian Kesan Mudarat (RM)">
                                <s:text name="nilaiMudarat${line_rowNum - 1}" value="${line.mudarat}" size="20" id="nilaiMudarat" formatPattern="#,##0.00"/>
                            </display:column>
                            <display:column title="Nilaian DLL (RM)">
                                <s:text name="nilaiDll${line_rowNum - 1}" value="${line.lainLain}" size="20" id="nilaiLain" formatPattern="#,##0.00"/>
                            </display:column>
                            <display:column title="Nilaian Keseluruhan (RM)">
                                <fmt:formatNumber pattern="#,##0.00" value="${line.amaunSebenar}"/>
                                <%--<s:text name="nilaiTanah${line_rowNum - 1}" value="${line.amaunSebenar}" size="20" id="nilaiTanah" formatPattern="#,##0.00"/>--%>
                            </display:column>
                            <display:column title="Amaun Deposit 125% (RM)">
                                <fmt:formatNumber pattern="#,##0.00" value="${line.amaunTuntutan}"/>
                            </display:column>
                            <%--<display:column title="Deposit" property="item"/>--%>
                            <%--<display:column title="Nilai Yang Diterima(RM)">
                                <fmt:formatNumber pattern="#,##0.00" value="${line.amaunTuntutan}"/>
                            </display:column>--%>
                            <display:column title="Status">
                                <c:if test="${actionBean.fp ne null}">
                                    <c:if test="${a.kodCaraBayaran ne null}">
                                        Bayar
                                    </c:if>
                                    <c:if test="${a.kodCaraBayaran eq null}">
                                        Belum Bayar
                                    </c:if>
                                </c:if>
                                <c:if test="${actionBean.fp eq null}">
                                    Belum Bayar
                                </c:if>
                            </display:column>
                            <display:column title="Cara Pembayaran" >
                                <c:if test="${actionBean.fp ne null}">
                                    <c:out value="${a.kodCaraBayaran.nama}"/>
                                </c:if>
                            </display:column>
                            <display:column title="Doc No.">
                                <c:if test="${actionBean.fp ne null}">
                                    <c:out value="${a.noRujukan}"/>
                                </c:if>
                            </display:column>
                            <display:column title="Tarikh" >
                                <c:if test="${actionBean.fp ne null}">
                                    <fmt:formatDate value="${b.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                                </c:if>
                            </display:column>
                            <display:column  title="Bank" >
                                <c:if test="${actionBean.fp ne null}">
                                    <c:out value="${a.bank.nama}"/>
                                </c:if>
                            </display:column>
                            <display:column  title="No Akaun" >
                                <c:if test="${actionBean.fp ne null}">
                                    <c:out value="${c.dokumenKewangan.idDokumenKewangan}"/>
                                </c:if>
                            </display:column>
                            <display:column  title="Kod Hasil" >
                                <c:if test="${actionBean.fp ne null}">
                                    <c:out value="${c.kodTransaksi.kod}"/>
                                </c:if>
                            </display:column>
                            <display:footer>
                        <tr>
                            <td colspan="3" align="left">Jumlah Keseluruhan (RM) :</td>
                            <td>
                                <div align="left"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalTanah}"/></div>
                            </td>
                            <td>
                                <div align="left"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalBangunan}"/></div>
                            </td>
                            <td>
                                <div align="left"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalPecah}"/></div>
                            </td>
                            <td>
                                <div align="left"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalMudarat}"/></div>
                            </td>
                            <td>
                                <div align="left"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalDll}"/></div>
                            </td>
                            <td>
                                <div align="left"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalNilaian}"/></div>
                            </td>
                            <td>
                                <div align="left"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalDeposit}"/></div>
                            </td>
                        </tr></display:footer>
                </display:table>
                <%--</td>
                </tr>--%>
            </table>
            <br>
            <table align="left">
                <tr>
                    <td>
                        Catatan :
                    </td>
                    <td>
                        &nbsp;<s:textarea name="catatan" id="catatan" cols="100" rows="3"/>
                    </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </table>
            <%--<table align="center" >
                    <tr>
                        <td><display:table class="tablecloth" name="${actionBean.senaraiDeposit}" pagesize="5" cellpadding="0" cellspacing="0"
                                requestURI="" id="line">
                     <display:column title="No" sortable="true">${line_rowNum}
                     <c:set var="a" value="${actionBean.caraBayaranList[line_rowNum-1]}"/>
                     <c:set var="b" value="${actionBean.dokumenKewanganBayaranList[line_rowNum-1]}"/>
                     <c:set var="c" value="${actionBean.transaksiList[line_rowNum-1]}"/>
                     </display:column>
                     <display:column title="Deposit" property="item"/>
                     <display:column title="Nilai Yang Diterima(RM)">
                         <fmt:formatNumber pattern="#,##0.00" value="${line.amaunTuntutan}"/>
                     </display:column>
                     <display:column title="Status">
                         <c:if test="${actionBean.caraBayaranList[line_rowNum-1] ne null}">
                             Bayar
                         </c:if>
                         <c:if test="${actionBean.caraBayaranList[line_rowNum-1] eq null}">
                             Belum Bayar
                         </c:if>
                     </display:column>
                     <display:column title="Cara Pembayaran" >
                         <c:out value="${a.kodCaraBayaran.nama}"/>
                     </display:column>
                     <display:column title="Doc No.">
                         <c:out value="${a.noRujukan}"/>
                     </display:column>
                     <display:column title="Tarikh" >
                         <fmt:formatDate value="${b.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>
                     </display:column>
                     <display:column  title="Bank" >
                         <c:out value="${a.bank.nama}"/>
                     </display:column>
                     <display:column  title="No Akaun" >
                         <c:out value="${c.akaunKredit.noAkaun}"/>
                     </display:column>
                     <display:column  title="Kod Hasil" >
                         <c:out value="${c.kodTransaksi.kod}"/>
                     </display:column>
                 </display:table></td>
                        </tr>

                </table>--%>

            <br>


            <%--  <p>
                  <label>&nbsp;</label>
                  <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
              </p>--%>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <legend>
                Deposit : Nilaian Tanah Oleh Jabatan Penilaian Dan Perkhidmatan Harta (JPPH)
            </legend>

            <table align="left" >
                <tr>
                    <td><label for="nolot">Nilaian Keseluruhan :</label></td>
                    <td>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalNilaian}"/></td>
                    <%--<td><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></td>--%>
                </tr>
                <tr>
                    <%--<s:text name="jumlahDeposit" size="20" id="jumlahDeposit" formatPattern="#,##0.00" onclick="klikNilaiTanah()"/>--%>
                    <td><label for="jumlahDeposit">Jumlah Kesuluruhan Deposit 125% :</label></td>
                    <td>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.totalDeposit}"/></td>
                </tr>


            </table>
        </fieldset>
    </div>
</s:form>
<%--<fieldset class="aras1">
            <legend>
                 Rekod
            </legend>

            <p>
                <label for="nolot">Nilaian Tanah :</label>
                <s:text name="noLot" size="20" id="noLot"/>
                <s:button name="simpanTanahTDK" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
            </p>
            <p>
           ${actionBean.hakmilik}
            <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/gantiRugi" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Deposit" style="vertical-align:center">
                            <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.item}"/>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Nilai (RM)">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.amaunTuntutan}"/>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Status" style="text-align:left">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.item}"/>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Cara Pembayaran)" style="text-align:right">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.amaunTuntutan}"/>
                            <br/>
                        </c:forEach>
                    </display:column>

                    <display:column title="Catatan" style="text-align:right">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.amaunSebenar}"/>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:column title="Tarikh Terima" style="text-align:right">
                        <c:forEach items="${actionBean.permohonanTuntutanKosList}" var="senarai">
                            <br/>
                            <c:out value="${senarai.amaunSebenar}"/>
                            <br/>
                        </c:forEach>
                    </display:column>
                    <display:footer>

                    </display:footer>
            </display:table>--%>
<%--   </p>--%>

<%--    </fieldset>--%>