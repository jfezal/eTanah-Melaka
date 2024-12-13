<%--
    Document   : pindahan_cara_bayaran
    Created on : Jan 28, 2010, 9:33:01 AM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?showEditPemohon&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function remove(f, id){
        var queryString = $(f).formSerialize()
        alert("Hapus ID Hakmilik : "+id+"?");
        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&"+queryString+"idHakmilik="+id;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        });
    }

    <%--function remove(id){

        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&idHakmilik="+id;
        document.form.action = url;
        document.form.submit();

    }--%>

    function change(a){
        <%--alert(a);--%>
        if (a == 'T'){
            $('#bank').hide();
            $('#caw').hide();
            $('#ruj').hide();
            $('#tarikh').hide();
        }
        else{
            $('#bank').show();
            $('#caw').show();
            $('#ruj').show();
            $('#tarikh').show();
        }
    }

</script>
<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindaan Cara Bayaran</font>
            </div>
        </td>
    </tr>
</table>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.PindaanCaraBayaranActionBean">
<s:errors />
<s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p>
                <label>Cara Bayar :</label>
                ${actionBean.caraBayaran.kodCaraBayaran.kod} -
                ${actionBean.caraBayaran.kodCaraBayaran.nama}&nbsp;
                <%--<fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy" />--%>
            </p>

            <c:if test="${actionBean.caraBayaran.kodCaraBayaran.kod ne 'T'}">
                <p>
                    <label>Bank :</label>
                    ${actionBean.caraBayaran.bank.nama}&nbsp;
                </p>

                <p>
                    <label>Cawangan :</label>
                    ${actionBean.caraBayaran.bankCawangan}&nbsp;
                </p>

                <p>
                    <label>Nombor Rujukan :</label>
                    ${actionBean.caraBayaran.noRujukan}&nbsp;
                </p>
            </c:if>

            <p>
                <label>Amaun : </label>
                <fmt:formatNumber value="${actionBean.caraBayaran.amaun}" type="currency" currencySymbol="RM "/>&nbsp;
            <br>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Cara Bayaran Baru</legend>
            <p>
                <label>Cara Bayaran :</label>
                <s:select name="kodCaraBayar" id="caraBayar" onchange="javaScript:change(this.value)" disabled="${actionBean.flag}">
                    <s:option value="0" label="Pilih ..." />
                    <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" />
                </s:select>
            </p>

            <p id="bank">
                <label>Bank :</label>
                <s:select name="kodBank" disabled="${actionBean.flag}">
                    <s:option label="Pilih..." value="0" />
                    <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                </s:select>
            </p>

            <p id="caw">
                <label>Cawangan :</label>
                <s:text name="caw" disabled="${actionBean.flag}"/>
            </p>

            <p id="ruj">
                <label>Nombor Rujukan :</label>
                <s:text name="noRujukan" disabled="${actionBean.flag}"/>
            </p>

            <p id="tarikh">
                <label>Tarikh :</label>
                <s:text name="tarikhCek" class="datepicker" readonly="true" disabled="${actionBean.flag}"/>
            </p>
            <%--<p>
                <label>Amaun</label>
                <s:text name="" />
            </p>--%>
        </fieldset>
    </div>

    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td align="right">
                <s:submit name="Step5" value="Simpan" class="btn"/>
                <c:if test="${actionBean.flag eq false}">
                    <s:submit name="Step3" value="Kembali" class="btn"/>
                </c:if>
                <c:if test="${actionBean.flag eq true}">
                    <s:submit name="Step1" value="Menu Utama" class="btn"/>
                </c:if>
            </td>
        </tr>
    </table>
</s:form>