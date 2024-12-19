<%-- 
    Document   : maklumat_hakmilik
    Created on : 12-Jan-2010, 18:29:31
    Author     : nordiyana
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>


<s:form  beanclass="etanah.view.stripes.pengambilan.MaklumatPermohonanActionBean">

    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>

            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}&nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.hakmilik.daerah.nama}&nbsp;
            </p>
            <p>
                <label>Seksyen :</label>
                ${actionBean.hakmilik.seksyen.nama}&nbsp;
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>
            <p>
                <label>Jenis Hakmilik :</label>
                ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
            </p>
            <p>
                <label>No Hakmilik :</label>
               &nbsp;
            </p>
            <p>
                <label>Kategori Tanah :</label>
                ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
            </p>
            <p>
                <label>Maklumat Atas Tanah :</label>
                ${actionBean.hakmilik.maklumatAtasTanah}&nbsp;
            </p>
            <p>
                <label>Kod Lot/PT :</label>
                ${actionBean.hakmilik.lot.nama}&nbsp;

            </p>
            <p>
                <label>No Lot/PT :</label>
                ${actionBean.hakmilik.noLot}&nbsp;

            </p>
            <p>
                <label>Keluasan :</label>
                <s:format formatPattern="#,##0.00" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
            </p>
            <p>
                <label>Syarat Nyata :</label>
                ${actionBean.hakmilik.syaratNyata.syarat}&nbsp;
            </p>
            <p>
                <label>Sekatan Kepentingan :</label>
                ${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;
            </p>
            <p>
                <label>No. Pelan Piawai :</label>
                ${actionBean.hakmilik.noLitho}&nbsp;
            </p>
            <p>
                <label>No. Pelan :</label>
                ${actionBean.hakmilik.noPelan}&nbsp;
            </p>
            <p>
                <label>Jenis Rizab :</label>
                ${actionBean.hakmilik.rizab.nama}&nbsp;&nbsp;
            </p>
            <p>
                <label>Tarikh Pemilikan Tanah :</label>
                <s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}" />
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>

    </div>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>

            <p>
                <label>ID Hakmilik :</label>
                <%--${actionBean.hakmilik.idHakmilik}--%>trial&nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                <%--${actionBean.hakmilik.daerah.nama}--%>trial&nbsp;
            </p>
            <p>
                <label>Seksyen :</label>
                <%--${actionBean.hakmilik.seksyen.nama}--%>trial&nbsp;
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <%--${actionBean.hakmilik.bandarPekanMukim.nama}--%>&nbsp;
            </p>
            <p>
                <label>Jenis Hakmilik :</label>
               <%-- ${actionBean.hakmilik.kodHakmilik.nama}--%>&nbsp;
            </p>
            <%--<p>
                <label>No Hakmilik :</label>
               &nbsp;
            </p>--%>
            <p>
                <label>Kategori Tanah :</label>
                <%--${actionBean.hakmilik.kategoriTanah.nama}--%>&nbsp;
            </p>
           <%-- <p>
                <label>Maklumat Atas Tanah :</label>
                ${actionBean.hakmilik.maklumatAtasTanah}&nbsp;
            </p>--%>
            <p>
                <label>Kod Lot/PT :</label>
                <%--${actionBean.hakmilik.lot.nama}--%>&nbsp;

            </p>
            <p>
                <label>No Lot/PT :</label>
                <%--${actionBean.hakmilik.noLot}--%>&nbsp;

            </p>
            <p>
                <label>Keluasan :</label>
               <%-- <s:format formatPattern="#,##0.00" value="${actionBean.hakmilik.luas}"/>--%>&nbsp; <%--${actionBean.hakmilik.kodUnitLuas.nama}--%>
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <%--${actionBean.hakmilik.syaratNyata.syarat}--%>&nbsp;
            </p>
            <p>
                <label>Sekatan Kepentingan :</label>
               <%-- ${actionBean.hakmilik.sekatanKepentingan.sekatan}--%>&nbsp;
            </p>
            <p>
                <label>No. Pelan Piawai :</label>
                ${actionBean.hakmilik.noLitho}&nbsp;
            </p>
            <p>
                <label>No. Pelan :</label>
                ${actionBean.hakmilik.noPelan}&nbsp;
            </p>
            <p>
                <label>Jenis Rizab :</label>
                ${actionBean.hakmilik.rizab.nama}&nbsp;&nbsp;
            </p>
          <%--  <p>
                <label>Tarikh Pemilikan Tanah :</label>
                <s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}" />
            </p>--%>
            <p>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>

    </div>

</s:form>



