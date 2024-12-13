<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript">
    $(document).ready(function() {
        var flg = ${actionBean.flagSimpan};
        if(flg){$('#btnSimpan').hide()}
    });
</script>
<script type="text/javascript">
    function edit(){
        window.open("${pageContext.request.contextPath}/pelarasanCukai?tambah", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=325");
    }
</script>
<s:useActionBean beanclass="etanah.view.stripes.hasil.PelarasanCukaiActionBean" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pelarasan Kod Cukai</font>
            </div>
        </td>
    </tr>
</table>
<s:form beanclass="etanah.view.stripes.hasil.PelarasanCukaiActionBean">
    <s:errors />
    <s:messages/>
    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun</legend>
            <p>
                <label>Nombor Hakmilik :</label>
                ${actionBean.akaun.noAkaun}
            </p>
            <p >
                <label>Nama Pembayar :</label>
                ${actionBean.akaun.pemegang.nama}
            </p>
            <p>
                <label>Bandar/Mukim/Pekan :</label>
                ${actionBean.akaun.hakmilik.bandarPekanMukim.nama}
            </p>
            <p>
                <label>Hakmilik :</label>
                ${actionBean.akaun.hakmilik.kodHakmilik.nama}
            </p>
        </fieldset>
    </div>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Transaksi</legend>
            <p>
                <label>Nombor Resit :</label>
                ${actionBean.transaksi.dokumenKewangan.idDokumenKewangan}&nbsp;
            </p>
            <p>
                <label>Kod Transaksi :</label>
                ${actionBean.transaksi.kodTransaksi.kod}&nbsp;
            </p>
            <p>
                <label>Jenis Transaksi :</label>
                ${actionBean.transaksi.kodTransaksi.nama}&nbsp;
            </p>
            <p>
                <label>Amaun :</label>
                <fmt:formatNumber value="${actionBean.transaksi.amaun}" currencySymbol="RM " type="currency"/>&nbsp;
                
            </p>
            <p>
                <label>Status :</label>
                ${actionBean.transaksi.status.nama}&nbsp;
            </p>
            <p>
                <label>Tarikh Masuk :</label>
                <fmt:formatDate value="${actionBean.transaksi.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/>&nbsp;
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.flag1}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Pelarasan</legend>
                <p class=instr>Sila Pilih jenis transaksi yang baru.</p>
                <p>
                    <label>Transaksi Baru : </label>
                    <s:select name="kodTransaksiBaru" style="width:500px;">
                        <s:option value="Sila Pilih ...">Sila Pilih ...</s:option>
                        <c:forEach items="${listUtil.senaraiKodTransaksi}" var="i" >
                            <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
                <p>
                    <label><em>*</em>Catatan : </label>
                    <s:textarea name="dokumenKewangan.catatan" style="height:150px;width:500px"/>
                </p>
                <br>
            </fieldset>
        </div>
    </c:if>

    <table bgcolor="green" width="100%">
        <tr>
            <td  align="right">
                <s:submit name="save" value="Simpan" class="btn" id="btnSimpan"/>
                <%--<s:submit name=" " value="Cetak" class="btn" style="display:${actionBean.print}"/>--%>
                <s:submit name="kembali" value="Keluar" class="btn"/>
            </td>
        </tr>
    </table>
</s:form>