<%-- 
    Document   : gantian_cek
    Created on : Jul 7, 2015, 12:12:16 PM
    Author     : haqqiem
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script>
    $(document).ready(function () {
        var flag = ${actionBean.flag};
        $("#new").hide();
        if (flag == false) {
            $("#details").hide();
        } else {
            $("#details").show();
        }
    });

    function editCheque(f) {
        var amt = parseFloat(${actionBean.amaun}).toFixed(2);
        $("#amaun").val(amt);
        $("#new").show();
        window.scrollTo(0, 1000);
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.unblockUI();
    }
    function change() {
        var c = $('#cb').val();
//        alert(c);
        if (c == 'T') {
            $('#bank').attr("disabled", "true");
            $('#caw').attr("disabled", "true");
            $('#ruj').attr("disabled", "true");
            $('#trh').attr("disabled", "true");
            $('#noAkaunPembayar').attr("disabled", "true");

        }
        else if (c == 'CL'||c=='CS'||c=='CT') {
            $('#bank').removeAttr("disabled");
            $('#caw').removeAttr("disabled");
            $('#ruj').removeAttr("disabled");
            $('#trh').removeAttr("disabled");
            $('#noAkaunPembayar').removeAttr("disabled");

        }
        else if((c == 'KW') || (c == 'WP')){
             $('#bank').val("PMB");
             $('#caw').removeAttr("disabled");
            $('#ruj').removeAttr("disabled");
            $('#trh').removeAttr("disabled");
            $('#noAkaunPembayar').attr("disabled", "true");
        }else{
            $('#bank').removeAttr("disabled");
            $('#caw').removeAttr("disabled");
            $('#ruj').removeAttr("disabled");
            $('#trh').removeAttr("disabled");
            $('#noAkaunPembayar').attr("disabled", "true");
            
        }
    }
        function save(f) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.unblockUI();
//        f.submit();
            var c = $('#cb').val();
//            alert(c);
             if (c == 'T') {
            return true;

        }
        else if (c == 'CL'||c=='CS'||c=='CT') {
         
            if($('#bank').val()==''){
                alert('Sila Pilih Bank');
                $('#bank').focus();
                return false;
            }
            if($('#caw').val()==''){
                alert('Sila Masukkan Cawangan');
                $('#caw').focus();
                return false;
            }
            if($('#ruj').val()==''){
                alert('Sila Masukkan No Rujukan');
                $('#ruj').focus();
                return false;
            }
            if($('#trh').val()==''){
                alert('Sila Masukkan Tarikh');
                $('#trh').focus();
                return false;
            }
            if($('#noAkaunPembayar').val()==''){
                alert('Sila Masukkan No Akaun Pembayar');
                $('#noAkaunPembayar').focus();
                return false;
            }
            return true;

        }
        else if((c == 'KW') || (c == 'WP')){
            if($('#caw').val()==''){
                alert('Sila Masukkan Cawangan');
                $('#caw').focus();
                return false;
            }
            if($('#ruj').val()==''){
                alert('Sila Masukkan No Rujukan');
                $('#ruj').focus();
                return false;
            }
            if($('#trh').val()==''){
                alert('Sila Masukkan Tarikh');
                $('#trh').focus();
                return false;
            }
            return true;
        }else{
            if($('#bank').val()==''){
                alert('Sila Pilih Bank');
                $('#bank').focus();
                return false;
            }
            if($('#caw').val()==''){
                alert('Sila Masukkan Cawangan');
                $('#caw').focus();
                return false;
            }
            if($('#ruj').val()==''){
                alert('Sila Masukkan No Rujukan');
                $('#ruj').focus();
                return false;
            }
            if($('#trh').val()==''){
                alert('Sila Masukkan Tarikh');
                $('#trh').focus();
                return false;
            }
          
            return true;
        }
            return false;
        }
</script>

<div align="center">
    <table style="width:100%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Utiliti Gantian Cek Tak Laku</font>
                </div>
            </td>
        </tr>
    </table>
</div>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.hasil.GantianCekTakLakuActionBean" id="gantian" name="form">
    <s:messages/>
    <s:errors/>
    <s:text name="caraBayar.idCaraBayaran" value="${actionBean.caraBayar.idCaraBayaran}" style="display:none"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Data</legend>
            <p>
                <label>Nombor Resit :</label>
                <s:text name="dokumenKewangan.idDokumenKewangan" id="resit" size="25" />
            </p>

            <%--            <p>
                            <label>Nombor Cek :</label>
                            <s:text name="caraBayar.noRujukan" id="resit" size="25" />
                        </p>--%>
            <p>
                <label>&nbsp;</label>
                <s:submit name="Step2" class='btn' value="Cari" id='search'/>
                <s:button name="" class='btn' value="Isi Semula" onclick="clearText('gantian');"/>&nbsp;
            </p><br>
        </fieldset>
    </div><br>
    <div class="subtitle" id="details">
        <fieldset class="aras1">
            <legend>Maklumat Resit</legend>
            <div class="instr-fieldset">
                <font color="red">Perhatian :</font><br>
                Sila tekan butang <img title="Sila Klik Untuk Gantian Cek" src='${pageContext.request.contextPath}/pub/images/edit.gif' /> untuk menggantikan cek baharu.<br>
                Medan bertanda <em>*</em> adalah mandatori.
            </div>
            <p>
                <label>Cara Bayaran :</label>
                ${actionBean.caraBayar.kodCaraBayaran.nama} &nbsp;
            </p>
            <p>
                <label>Cara Bayaran :</label>
                ${actionBean.caraBayar.bank.nama} &nbsp;
            </p>
            <p>
                <label>Cawangan:</label>
                ${actionBean.caraBayar.bankCawangan} &nbsp;
            </p>
            <p>
                <label>Amaun Bayaran (RM)  :</label>
                <fmt:formatNumber value="${actionBean.caraBayar.amaun}" pattern="#,###,###.00"/> &nbsp;
            </p>
            <p>
                <label>Dibayar Oleh :</label>
                ${actionBean.dokumenKewangan.isuKepada} &nbsp;
            </p>

            <p>
                <label>No. Rujukan Cek :</label>
                ${actionBean.caraBayar.noRujukan} 
                <a id="" onclick="editCheque(this.form);" onmouseover="this.style.cursor = 'pointer';">
                    <img title="Sila Klik Untuk Gantian Cek" src='${pageContext.request.contextPath}/pub/images/edit.gif' />
                </a>&nbsp;
            </p>

            <p>
                <label>Tarikh Cek :</label>
                <fmt:formatDate value="${actionBean.caraBayar.tarikhCek}" pattern="dd/MM/yyyy hh:mm aa"/>
            </p>

            <br>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiResit}" 
                               requestURI="/hasil/gantian_resit" id="line">
                    <display:column title="Bil" sortable="true" > <div align="center">${line_rowNum}</div></display:column>
                    <display:column title="Nombor Resit Terlibat" property="idDokumenKewangan"/>$

                    <display:column title="Transaksi" >
                        <c:forEach items="${line.senaraiTransaksi}" var="senarai" varStatus="a">
                            <c:out value="${senarai.kodTransaksi.kod}" /> - <c:out value="${senarai.kodTransaksi.nama}" /> 
                            <c:if test="${senarai.kodTransaksi.kod eq '61401' 
                                          or senarai.kodTransaksi.kod eq '61402' 
                                          or senarai.kodTransaksi.kod eq '76152' }">
                                  (<c:out value="${senarai.untukTahun}" />)
                            </c:if>
                            <br>
                        </c:forEach>
                    </display:column>

                    <display:column title="Nombor Akaun" >
                        <c:if test="${line.akaun.noAkaun ne null}">
                            <c:out value="${line.akaun.noAkaun}"/>
                        </c:if>
                        <c:if test="${line.akaun.noAkaun eq null}">
                            -
                        </c:if>
                    </display:column>

                    <display:column title="Tarikh Masuk">
                        <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                    </display:column>

                    <display:column title="Dimasuk Oleh" >
                        <c:out value="${line.infoAudit.dimasukOleh.nama}"/>
                    </display:column>

                </display:table>
            </div>
        </fieldset>
    </div>

    <div class="subtitle" id="new">
        <fieldset class="aras1">
            <legend>Maklumat Resit Baru</legend>
            <p>
                <label><em>*</em>Cara Bayaran :</label>
                <s:select name="kodCaraBayar" id="cb" onchange="javaScript:change()">
                    <s:option label="Pilih ..."  value="0" />
                    <c:forEach items="${actionBean.senaraiKodCaraBayaran}" var="i" >
                        <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>

            <p>
                <label><em>*</em>Bank :</label>
                <s:select name="bank" id="bank">
                    <s:option label="Pilih ..."  value="0" />
                    <c:forEach items="${actionBean.senaraiKodBank}" var="i" >
                        <s:option value="${i.kod}">${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>

            <p>
                <label><em>*</em>Cawangan  :</label>
                <s:text name="cawangan" id="caw" size="28" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label><em>*</em>No.Rujukan  :</label>
                <s:text name="noRujukan" id="ruj" size="28"/>
            </p>

            <p>
                <label><em>*</em>Tarikh :</label>
                <s:text name="tarikhCek" id="trh" size="28" readonly="true" maxlength="10" class="datepicker"/>
            </p>
            <p>
                <label><em>*</em>No Akaun Bank :</label>
                <s:text name="noAkaunPembayar" id="noAkaunPembayar" size="28"/>
            </p>
            <p>
                <label>Amaun (RM)  :</label>
                <s:text name="amaun" id="amaun" size="28" readonly="true" style="text-align:right;"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="Step3" class='btn' value="Simpan" id='search' onclick="return save(this.form)"/>
                <s:button name="" class='btn' value="Isi Semula" onclick="clearText('gantian');"/>&nbsp;
            </p><br>
        </fieldset>
    </div>

    <table style="width:100%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"> </font>
                </div>
            </td>
        </tr>
    </table>
</s:form>