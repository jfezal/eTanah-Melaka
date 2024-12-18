<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/etanah/pub/js/ageCalculator.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Permohonan Batal Perserahan</title>

    </head>
    <body>
        <script type="text/javascript">
    function change(id){

        if(id == "LL"){
            if($("#catatan").val() == ""){
                alert('Sila isi ruangan " Catatan ".');
                $("#catatan").focus();
                return true;
            }
        }

    }
</script>
        <s:messages />
        <s:errors />

        <s:form action="/daftar/batal_perserahan" name="form1">
            <s:hidden name="permohonan.idPermohonan"></s:hidden>
            <fieldset class="aras1">

                <br>
                <legend>Permohonan Batal Perserahan</legend>
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.permohonan}" class="tablecloth">
                        <display:column property="idPermohonan" title="Id Permohonan" />
                        <display:column property="kodUrusan.kod" title="Kod Urusan" />
                        <display:column property="kodUrusan.nama" title="Perihal"/>
                        <display:column property="infoAudit.tarikhMasuk" format="{0,date,dd-MM-yyyy}" title="Tarikh Perserahan/Permohonan" />
                    </display:table>
                </div>
                <br>

            </fieldset>

            <c:if test="${actionBean.transList != null}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Senarai Cara Bayaran
                        </legend>
                        <div class="content" align="center">

                            <display:table class="tablecloth" name="${actionBean.transList}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/pembatalan_resit">
                                <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                    <display:column  title="No Akaun" >
                                        ${line.akaunKredit.noAkaun}</display:column>
                                </c:if>
                                <display:column  title="Jenis Transaksi" >
                                    ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>

                                <display:column title="Cara Bayaran">
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                        <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}"/><br>
                                    </c:forEach>
                                </display:column>

                                <display:column title="Bank / Agensi Pembayaran">
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                        <c:out value="${senarai.caraBayaran.bank.nama}"/><br>
                                    </c:forEach>
                                </display:column>
                                <display:column title="No Rujukan">
                                    <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                        <c:out value="${senarai.caraBayaran.noRujukan}"/><br>
                                    </c:forEach>
                                </display:column>
                                <display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" format="{0,date,dd/MM/yyyy,hh:mm aa}" style="text-align:right"/>
                                <display:column property="amaun" title="Jumlah" format="{0,number, 0.00}" style="text-align:right"/>
                            </display:table>
                        </div>


                    </fieldset>


                </div>
            </c:if>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Alasan Pembatalan</legend>
                    <p>
                        <label for="batal">Sebab :</label>
                        <s:textarea name="sebab"  id="sebab" rows="3" cols="50" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label for="catatan">Catatan :</label>
                        <s:textarea name="catatan"  id="catatan" rows="3" cols="50" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <tr>
                    <br>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="saveBatal" value="Simpan" class="btn"   />
                        <s:submit name="kembali" value="Kembali" class="btn" id="btn4"/>
                    </p>

                    </tr>

                </fieldset>
            </div>


        </s:form>

    </body>
</html>