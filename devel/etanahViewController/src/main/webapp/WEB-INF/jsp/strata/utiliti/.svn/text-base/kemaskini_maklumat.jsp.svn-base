<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Kemaskini Data Permohonan Strata</title>

        <script type="text/javascript">
            function viewPenyerah(idMohon){
                window.open("${pageContext.request.contextPath}/strata/kemaskini_maklumat_penyerah?viewMaklumatPenyerah&idPermohonan=" + idMohon, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
            }
            
            function viewPemohon(idMohon){
                window.open("${pageContext.request.contextPath}/strata/kemaskini_maklumat_pemohon?viewMaklumatPemohon&idPermohonan=" + idMohon, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
            }
            
            function viewPerbadanan(idMohon){
                window.open("${pageContext.request.contextPath}/strata/kemaskini_maklumat_perbadanan?viewMaklumatPerbadanan&idPermohonan=" + idMohon, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
            }
        </script>
    </head>

    <body>

    <stripes:errors/>
    <stripes:messages/>

    <stripes:form beanclass="etanah.view.strata.utiliti.KemaskiniDataPermohonanStrataActionBean">

        <fieldset class="aras1">
            <legend>Maklumat Permohonan Strata</legend>
            <br/>
            <p><label for="idPermohonan">ID Permohonan :</label> ${actionBean.permohonan.idPermohonan}</p>

            <p><label for="kodUrusan">Kod Urusan/Urusan :</label> ${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}</p>
            <br/>
        </fieldset>

        <fieldset class="aras1">
            <legend>Kemaskini Data</legend>
            <br/>
            <p>
                1. <a href="#" title="Sila klik untuk kemaskini maklumat penyerah" onclick="viewPenyerah('${actionBean.permohonan.idPermohonan}');return false;">Maklumat Penyerah</a>
                <br/>
                2. <a href="#" title="Sila klik untuk kemaskini maklumat pemohon" onclick="viewPemohon('${actionBean.permohonan.idPermohonan}');return false;">Maklumat Pemohon</a>
                <br/>
                3. <a href="#" title="Sila klik untuk kemaskini maklumat perbadanan pengurusan" onclick="viewPerbadanan('${actionBean.permohonan.idPermohonan}');return false;">Maklumat Perbadanan Pengurusan</a>
            </p>
            <br/>
        </fieldset>

    </stripes:form>
</body>
</html>
