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
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">

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
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.DepositActionBean" >
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Deposit : Nilaian Tanah Oleh Jabatan Penilaian Dan Perkhidmatan Harta (JPPH)
            </legend>
           
            <table align="left" >
                <tr>
                    <td><label for="nolot">Nilaian Tanah (RM):</label></td>
                    <td><s:text name="nilaiTanah" size="20" id="noLot"/></td>
                    <td><c:if test="${actionBean.simpan eq 'true'}" >
                            
                        </c:if>
                        <%--<c:if test="${actionBean.simpan eq 'false'}">--%>
                            
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <%--</c:if>--%>  
                    </td>
                </tr>


            </table>
        </fieldset>



    </div>
    <br>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Jadual Pembayaran Deposit
            </legend>
            <table align="center" >
                <tr>
                    <td><display:table class="tablecloth" name="${actionBean.senaraiDeposit}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="" id="line">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <c:set var="a" value="${actionBean.caraBayaranList[line_rowNum-1]}"/>
                                <c:set var="b" value="${actionBean.dokumenKewanganBayaranList[line_rowNum-1]}"/>
                                <c:set var="c" value="${actionBean.transaksiList[line_rowNum-1]}"/>
                            </display:column>
                            <display:column title="Deposit" property="item"/>
                            <display:column title="Nilai Yang Diterima(RM)" property="amaunTuntutan"/>
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

            </table>

            <br>


          <%--  <p>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>--%>
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