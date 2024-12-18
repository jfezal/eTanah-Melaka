<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function edit(f, id1){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/pindaan_kutipan?cari&"+queryString+"&accBaru="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function addRows(radioBtn){
        $('#hakmilik').val(radioBtn);
        refreshPage();
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/hasil/pindaan_kutipan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindahan Akaun Amanah</font>
                </div>
            </td>
        </tr>
    </table></div>

<s:form beanclass="etanah.view.stripes.hasil.PindaanAkaunAmanahActionBean">
    <s:hidden name="akaun.noAkaun"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kutipan</legend>
            <p>
                <label>Tarikh :</label>
                <fmt:formatDate value="${actionBean.akaun.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy" />
                &nbsp;
            </p>

            <p>
                <label>Jumlah Bayaran :</label>
                <fmt:formatNumber value="${actionBean.akaun.baki}" type="currency" currencySymbol="RM "/>
                &nbsp;
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitles">
        <fieldset class="aras1">
            <legend>Maklumat Pembayar</legend>
            <p>
                <label>Nama : </label>
                ${actionBean.akaun.pemegang.nama}
            </p>

            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.akaun.pemegang.noPengenalan}
            </p>

            <p>
                <label>Alamat Surat Menyurat :</label>
                <c:if test="${actionBean.akaun.pemegang.alamat1 ne null}">${actionBean.akaun.pemegang.alamat1}</c:if>
            </p>
            <c:if test="${actionBean.akaun.pemegang.alamat2 ne null}">
                <p>
                    <label>&nbsp</label>
                    ${actionBean.akaun.pemegang.alamat2}
                </p>
            </c:if>
            <c:if test="${actionBean.akaun.pemegang.alamat3 ne null}">
                <p>
                    <label>&nbsp</label>
                    ${actionBean.akaun.pemegang.alamat3}
                </p>
            </c:if>
            <c:if test="${actionBean.akaun.pemegang.alamat4 ne null}">
                <p>
                    <label>&nbsp</label>
                    ${actionBean.akaun.pemegang.alamat4}<br>
                </p>
            </c:if>

            <p>
                <label>Poskod :</label>
                ${actionBean.akaun.pemegang.poskod}
            </p>

            <p>
                <label>Negeri :</label>
                ${actionBean.akaun.pemegang.suratNegeri.nama}
            </p>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend>
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.hakmilik.cawangan.daerah.nama}
            </p>
            <p>
                <label>Bandar/ Pekan/ Mukim :</label>
                ${actionBean.hakmilik.bandarPekanMukim.nama}
            </p>
            <p>
                <label>Jenis Hakmilik :</label>
                ${actionBean.hakmilik.kodHakmilik.nama}
            </p>
            <p>
                <label>No. Lot/PT :</label>
                ${actionBean.hakmilik.noLot}
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun Amanah</legend>
            <p>
                <label>Nombor Akaun Amanah :</label>
                ${actionBean.akaun.noAkaun}
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Transaksi</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" requestURI="/hasil/ansuran" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh" format="{0,date,dd/MM/yyyy}"/>
                    <display:column title="Transaksi">
                        ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                    </display:column>
                    <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit"/>
                    <display:column property="amaun" title="Amaun (RM)" style="text-align:right" format="{0,number, 0.00}"/>
                    <display:column title="Status" property="status.nama"/>
                    <display:footer>
                        <tr>
                            <td colspan="4"><div align="right"><b>Jumlah (RM)</b></div></td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.total}" pattern="0.00"/></div></td>
                        <tr>
                        </display:footer>
                    </display:table>
            </div>
            <br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun</legend>
            <div align="center" id="msg"></div>
            <p>
                <label >Nombor Akaun Cukai Tanah :</label>
                <s:text name="accBaru" id="hakmilik"/>
                <s:button name=" " onclick="edit(this.form);" id="hakmilik" value="Cari" class="btn"/>
            </p>
            <br>
        </fieldset>
    </div>

   <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                <s:submit name="save" value="Terus" id="daftar" class="btn"/>
                <%--<s:reset name=" " value="Isi Semula" class="btn"/>--%>
                <s:submit name="kembali" value="Kembali" class="btn"/>
            </td>
        </tr>
       </table></div>s

</s:form>