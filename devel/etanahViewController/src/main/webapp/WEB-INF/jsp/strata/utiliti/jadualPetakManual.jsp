
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    $(document).ready(function() {
        $('#bngnTanah').hide();
        $('#bngn').hide();
        $('#tanah').hide();
        $('#mezanin2').hide();
        $('#senaraimenara').hide();
        $('#tgktBwh').hide();
        $('#bilPelan1').show();
        $('#bilPelanByk').hide();
        for(var i=0; i<${fn:length(actionBean.senaraibngnPetak)}; i++ ){
            document.getElementById("simpanPetakEdit"+i).disabled = true;
        }

        for(var i=0; i<${fn:length(actionBean.bngnnPetak)}; i++ ){
            document.getElementById("simpanPetakEdit2"+i).disabled = true;
        }


    <%--<c:if test = "${actionBean.cekLanded eq null and (empty actionBean.cekMhnBngn)}">
        var petakmula = ${actionBean.petakMula};

            var petak = ${actionBean.bgnTingkat.bilanganPetak};
            var count = petakmula + petak;
            for(var i=petakmula; i<=count; i++){
                document.getElementById("petakAksesori"+i).disabled = true;
            }
    </c:if>--%>
        });

        function tanah() {
            var pilih = document.getElementById("pickjenis1").value;
            $('#tanah').show();
            $('#bngnTanah').hide();
        }
        function bngnTanah() {
            var pilih = document.getElementById("pickjenis2").value;
            $('#tanah').hide();
            $('#bngnTanah').show();
        }
</script>
<script language="javascript" type="text/javascript">

    function changeVal(value) {
        if (value === "y") {
            $('#mezanin2').show();
        }
        if (value === "t") {
            $('#mezanin2').hide();
        }
    }
    function menara2(value) {
        if (value === "y") {
            $('#senaraimenara').show();
        }
        else{
            $('#senaraimenara').hide();
        }
    }
    function kongsiTgkt2(value) {
        if (value === "y") {
            $('#tgktBwh').show();
        }
        else{
            $('#tgktBwh').hide();
        }
    }
    function pelanByk2(value) {
        if (value === "y") {
            $('#bilPelanByk').show();
            $('#bilPelan1').hide();
        }
        if (value === "t") {
            $('#bilPelanByk').hide();
            $('#bilPelan1').show();
        }
    }
    function enableSimpan2(count,elmnt,inputTxt){
        document.getElementById("simpanPetakEdit2"+count).disabled = false;

    }
</script>
<script language="javascript" type="text/javascript">
    function enableSimpan(count,elmnt,inputTxt){
        document.getElementById("simpanPetakEdit"+count).disabled = false;

    }

    function popup2(f)
    {
        window.open("${pageContext.request.contextPath}/JadualPetakManual?petakAksesoriTanahPopup&petakAksTanah=" + f, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
    }

    function kemaskiniPetakTanah(event, f){
    <%--alert("kemaskiniPetakTanah");--%>
            var q = $(f).formSerialize();
            var url;
            var len = ${actionBean.cekLanded.bilanganPetak};

            for(var i=1; i<=len; i++){
                var va = $('#bil'+i).val();
                var unit_SyerTanah = $('#unit_SyerTanah'+i).val();
                var luasTanah = $('#luasTanah'+i).val();
                var kodKegunaanPetakTanah = $('#kodKegunaanPetakTanah'+i).val();
    <%--alert(kodKegunaanPetakTanah);--%>

                url = '${pageContext.request.contextPath}/JadualPetakManual?kemaskiniPetakTanah&bil=' + va + '&unit_SyerTanah='+unit_SyerTanah + '&luasTanah=' +luasTanah + '&kodKegunaanPetakTanah=' +kodKegunaanPetakTanah;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            alert("Maklumat Petak Tanah Berjaya Disimpan");
        }
</script>

<script type="text/javascript">

    function doViewReport(v) {
    <%--var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
    window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");--%>
            var idmohon = '${actionBean.mohon.idPermohonan}';
            var kodnegeri = '${actionBean.kodnegeri}';

            if(kodnegeri == '04'){
                var report = 'STRJadualPetak_MLK.rdf';
            }
            if(kodnegeri == '05'){
                var report = 'STRJadualPetak_NS.rdf';
            }
            var url = "reportName="+report+"%26report_p_id_mohon="+idmohon;
            //alert(url);
            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        }

        function deleteJadual(y,frm) {
            var idmohon = '${actionBean.mohon.idPermohonan}';
            var url = '${pageContext.request.contextPath}/JadualPetakManual?delete';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function hapusTingkat(tingkat,x,frm) {

            var idbngn = '${actionBean.mhnbgn.idBangunan}';
            var url = '${pageContext.request.contextPath}/JadualPetakManual?deleteTingkat&tingkat='+tingkat+'&idbngn='+idbngn;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

</script>
<script type="text/javascript">
    function simpanEdit(x,y,f,frm){
        var kodKegunaanPetakTanah = $('#kodKegunaanPetakTanah'+y).val();
        var syerEdit = $('#syerEdit'+y).val();
        var luasEdit = $('#luasEdit'+y).val();

        var url = '${pageContext.request.contextPath}/JadualPetakManual?simpanPetakTanah&petak='+ x + '&kodKegunaanPetakTanah='+kodKegunaanPetakTanah + '&syerEdit='+syerEdit + '&luasEdit='+luasEdit;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function simpanPetakDetails(x,idpetak,y,f,frm){
    <%--alert("simpanPetakDetails");--%>
            var idbngn = $('#idbngn').val();
            var idtgkt = $('#idtgkt').val();
            var pabPetakEdit = $('#pabPetakEdit'+y).val();
            var idpetak = idpetak;
            var kodKegunaanPetak = $('#kodKegunaanPetak'+y).val();
            var syerEdit = $('#syerEdit'+y).val();
            var luasEdit = $('#luasEdit'+y).val();

            var url = '${pageContext.request.contextPath}/JadualPetakManual?kemaskini5&petak='+ x + '&kodKegunaanPetak='+kodKegunaanPetak + '&syerEdit='+syerEdit
                + '&luasEdit='+luasEdit+ '&pabPetakEdit='+pabPetakEdit+ '&idpetak='+idpetak+ '&idbngn='+idbngn+ '&idtgkt='+idtgkt;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function hapusPetak(x,y,frm)
        {
            var url = '${pageContext.request.contextPath}/JadualPetakManual?hapusPetakTanah&petakTanahHapus='+ x ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function hapusPetak2(x,y,b,frm)
        {
            var url = '${pageContext.request.contextPath}/JadualPetakManual?hapusPetak&petakHapus='+ x+'&idPetak='+ y ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function popupTanah(f)
        {
            window.open("${pageContext.request.contextPath}/JadualPetakManual?petakAksesoriTanahPopup&petakAksTanah=" + f, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
        }

        function showReportJP(){
            window.open("${pageContext.request.contextPath}/JadualPetakManual?genReportJP", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
</script>
<script type="text/javascript">
    function nonNumber(elmnt,inputTxt){
        var a = document.getElementById('bilBangunan');

        if (isNaN(a.value)){
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#bilBangunan").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
    }

    function popup(f,x)
    {

        var idbngn = $('#idbngn').val();
        var idtgkt = $('#idtgkt').val();


        window.open("${pageContext.request.contextPath}/JadualPetakManual?petakAksesoriPopup&petakAks=" + f + '&bangunan='+idbngn + '&tingkat='+idtgkt+ '&idpetak='+x, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
    }

    function pilihjenis(event, f){

        var q = $(f).formSerialize();
        var url;
        var bilBngn = document.getElementById("bilBangunan").value;
        if($('#pickjenis1').is(':checked')){
            var va = $('#pickjenis1').val();
            url = '${pageContext.request.contextPath}/JadualPetakManual?kemaskini&pickjenis=' + va + '&bilBangunan='+bilBngn;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    

    <%--function simpanPetakDetails(event, f){
        doBlockUI();
        var q = $(f).formSerialize();
        var url;
        var len = ${actionBean.petak};
        var bangunan = document.getElementById("bangunan").value;
        var tingkat = document.getElementById("tingkat").value;
        var petakMula = document.getElementById("petakMula").value;

        for(var i=0; i<len; i++){
            var count = parseInt(petakMula) + parseInt(i);
            var va = $('#bil'+count).val();
            var unitSyer = $('#unit_Syer'+count).val();
            var luas = $('#luas'+count).val();
            var kodKegunaanPetak = $('#kodKegunaanPetak'+count).val();

            url = '${pageContext.request.contextPath}/JadualPetakManual?kemaskini5&bil=' + va + '&bangunan='+bangunan + '&tingkat='+tingkat + '&unitSyer='+unitSyer + '&luas=' +luas + '&kodKegunaanPetak=' +kodKegunaanPetak;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        doUnBlockUI();
        alert("Maklumat Petak Berjaya Disimpan");
    
    }--%>

    <%--<c:if test = "${edit}">
            var petakmula = ${actionBean.petakMula};
            var petak = ${actionBean.bgnTingkat.bilanganPetak};
            var count = petakmula + petak;
            for(var i=petakmula; i<=count; i++){
                document.getElementById("petakAksesori"+i).disabled = false;
            }
    </c:if>--%>
            

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.JadualPetakManual">

    <s:messages />
    <s:errors />
    <s:hidden name="idbngn" id="idbngn" value="${actionBean.mhnbgn.idBangunan}"/>
    <c:if test="${xml}">
        <p><font color="red">Arahan : Sila hapus Jadual Petak XML terlebih dahulu.</font></p>
    </c:if>
    <c:if test="${!xml}">
        <font color="blue"><b>Kemasukan Jadual Petak Secara Manual</b></font>
        <p></p>
        <center>
            <c:if test="${actionBean.cekLanded ne null or (!empty actionBean.cekMhnBngn)}">
                Sila klik butang 'Jana Jadual Petak' setelah selesai mengisi maklumat.
                <br><br>
                <s:button name="" value="Papar Jadual Petak" class="longbtn" onclick="doViewReport('${actionBean.mohon.idPermohonan}');" />
                <s:button name="delete" value="Hapus Jadual Petak" class="longbtn" onclick="deleteJadual()" />
                <br>
                <br>
                <p align="center">
                    <s:button name="genReportJP" id="report" value="Jana Jadual Petak" class="longbtn" onclick="showReportJP();"/>&nbsp;
                </p>
            </c:if>
        </center>

        <c:if test="${actionBean.mohon.kodUrusan.kod eq 'PBBD'}">
            <br>
            <label>Pilih Jenis Bangunan : </label>
            <c:if test="${actionBean.pickjenis eq null and actionBean.cekLanded eq null and (empty actionBean.cekMhnBngn)}">
                <input type="radio" name="pickjenis" id="pickjenis1" value="tanah" onclick="tanah()"/>Tanah Sahaja
                <input type="radio" name="pickjenis" id="pickjenis2" value="bngnTanah" onclick="bngnTanah()"/>Bangunan dan Tanah Sahaja
                <%--<s:radio name="pickjenis" id="pickjenis3" value="bngn"/>Bangunan Sahaja--%>
            </c:if>
            <c:if test="${actionBean.pickjenis eq 'bngnTanah'}">
                <input type="radio" name="pickjenis" id="pickjenis1" value="tanah" onclick="tanah()"/>Tanah Sahaja
                <input type="radio" name="pickjenis" id="pickjenis2" value="bngnTanah" checked="true" onclick="bngnTanah()"/>Bangunan dan Tanah Sahaja
                <br>
            </c:if>
            <c:if test="${actionBean.pickjenis eq 'tanah'}">
                <input type="radio" name="pickjenis" id="pickjenis1" value="tanah" checked="true" onclick="tanah()"/>Tanah Sahaja
                <input type="radio" name="pickjenis" id="pickjenis2" value="bngnTanah" onclick="bngnTanah()"/>Bangunan dan Tanah Sahaja
                <br>
            </c:if>
            <c:if test="${actionBean.pickjenis eq null and actionBean.cekLanded ne null and (!empty actionBean.cekMhnBngn)}">
                <input type="radio" name="pickjenis" id="pickjenis1" value="tanah" onclick="tanah()"/>Tanah Sahaja
                <input type="radio" name="pickjenis" id="pickjenis2" value="bngnTanah" checked="true" onclick="bngnTanah()"/>Bangunan dan Tanah Sahaja
                <br>
            </c:if>

        </c:if>
        <c:if test="${actionBean.mohon.kodUrusan.kod ne 'PBBD'}">
            <br><br>
            <label><font color="red"><em>*</em></font>Bilangan Bangunan : </label>
            <s:text name="bilBangunan" id="bilBangunan" size="4" onblur="javascript:nonNumber(this, this.value);"/>
            <c:if test="${fn:length (actionBean.senaraiPermohonanBangunan) == 0}">
                <center>
                    <s:button class="btn" value="Kemaskini" name="kemaskini" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </center>
            </c:if>
        </c:if>
        <div id="tanah">
            <br>
            <label><font color="red"><em>*</em></font>Bilangan Petak Tanah : </label>
            <s:text name="bilTanah" id="bilTanah" size="4" onblur="javascript:nonNumber(this, this.value);"/><br>
            <label><font color="red"><em>*</em></font>Jumlah Syer Petak Tanah : </label>
            <s:text name="syer" id="syer" size="7" onblur="javascript:nonNumber(this, this.value);"/>
            <center>
                <s:button class="btn" value="Simpan" name="kemaskini" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </center>
        </div>
        <div id="bngnTanah">
            <label>Bilangan Bangunan : </label>
            <s:text name="bilBangunan" id="bilBangunan" size="4" onblur="javascript:nonNumber(this, this.value);"/><br>
            <label>Bilangan Petak Tanah : </label>
            <s:text name="bilTanah2" id="bilTanah2" size="4" onblur="javascript:nonNumber(this, this.value);"/><br>
            <label>Jumlah Syer Petak Tanah : </label>
            <s:text name="syer2" id="syer2" size="7" onblur="javascript:nonNumber(this, this.value);"/>
            <center>
                <s:button class="btn" value="Simpan" name="kemaskini" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </center>
        </div>

        <c:if test="${actionBean.cekLanded ne null}">
            <fieldset>
                <font color="blue"><b>Maklumat Petak Tanah</b></font>
                <p>
                    <label>Bilangan Petak Tanah:</label>
                    ${actionBean.cekLanded.bilanganPetak}
                </p>
                <p>
                    <label>Jumlah Syer Petak Tanah : </label>
                    ${actionBean.cekLanded.syerBlok}
                </p>

                <br><br>
                <c:if test="${!empty actionBean.senaraibngnPetak && actionBean.senaraibngnPetak[0].luas eq null}">
                    <legend>Setiap Petak Tanah</legend>
                    <p>
                        <label>Unit Syer :</label>
                        <s:text name="unit_SyerTanah" id="unit_SyerTanah" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p>
                        <label>Luas (Meter Persegi) :</label>
                        <s:text name="luasTanah" id="luasTanah" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p>
                        <label>Kegunaan Petak :</label>
                        <s:select name="kodKegunaanPetakTanah" id="kodKegunaanPetakTanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <center>
                        <s:button class="longbtn" value="Kemaskini Petak Tanah" name="kemaskiniL" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </center>
                </c:if>
                <p></p>
                <p></p>

                <c:if test="${!empty actionBean.senaraibngnPetak && actionBean.senaraibngnPetak[0].luas ne null}">
                    <center>
                        <p>
                            <font color="red">Sila tekan butang 'Simpan' jika ada perubahan.</font>
                        </p>
                        <c:set value="0" var="count"/>
                        <display:table style="width:40%" class="tablecloth" name="${actionBean.senaraibngnPetak}" pagesize="100" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Petak"><s:text name="petak${count}" id="petak${count}" value="${line.nama}" readonly="true"/></display:column>
                            <display:column title="Kegunaan Petak">
                                <s:select name="kodKegunaanPetakTanah${count}" id="kodKegunaanPetakTanah${count}" value="${line.kegunaan.kod}" onchange="javascript:enableSimpan('${count}',this, this.value);">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                                </s:select>
                            </display:column>
                            <display:column title="Luas (Meter Persegi)"><s:text name="luasEdit${count}" id="luasEdit${count}" value="${line.luas}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                            <display:column title="Syer"><s:text name="syerEdit${count}" id="syerEdit${count}" value="${line.syer}" onchange="javascript:enableSimpan('${count}',this, this.value);"/></display:column>
                            <display:column title="Petak Aksesori">
                                <c:forEach items="${actionBean.senaraiPetakAksesoriTanahbyIdTgkt}" var="line2">
                                    <c:if test="${line.idPetak eq line2.petak.idPetak}">
                                        A${line2.nama}
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Tindakan">
                                <s:button class="longbtn" name="simpanPetakTanah" value="Simpan" id="simpanPetakEdit${count}" onclick="simpanEdit('${line.nama}','${count}',this.value,this.form);"/><%--simpanEdit('${line.nama}','${count}',this.value,this.form);--%>
                                <s:button class="longbtn" name="petakAksesori" value="Petak Aksesori" onclick="popupTanah('${line.nama}');"/>
                                <s:button class="longbtn" name="hapusPetakTanah" value="Hapus" id="hapus" onclick="hapusPetak('${line.nama}',this.value,this.form);"/><%--hapusPetak('${line.nama}',this.value,this.form);--%>
                            </display:column>
                            <c:set value="${count +1}" var="count"/>
                        </display:table>

                    </center>
                </c:if>
                <br><br>
            </fieldset>
        </c:if>
        <br>
        <br>
        <br>
        <p></p>
            <c:if test="${fn:length (actionBean.senaraiPermohonanBangunan) > 0 && fn:length (actionBean.bilBangunan) >0}">
                <fieldset>
                    <font color="blue"><b>Bilangan Bangunan</b></font><br>
                    <label>Bangunan :</label>
                    <s:select name="bangunan" id="bangunan">
                        <c:forEach var="i" begin="1" end="${actionBean.bilBangunan}" >
                            <%--<s:option value="${actionBean.bilBangunan}" id="${i - 1}" style="width:400px">--%>
                            <s:option id="${i}" style="width:50px">${i}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:button class="btn" value="Cari" name="kemaskini2" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>

            <p></p>
            <p></p>
            <p></p>
            <c:if test="${edit}">
                <%--<c:if test="${actionBean.mhnbgn.syerBlok eq 0}">--%>
                <p><label><font color="red"><em>*</em></font>Pilih Jenis Bangunan : </label>
                    <c:if test="${actionBean.mhnbgn.kekal eq 'Y'}">
                        <input type="radio" checked="checked" name="kekal" id="kekal" value="kekal"/>Kekal
                        <input type="radio" name="kekal" id="xkekal" value="provisional"/>Sementara
                    </c:if>
                    <c:if test="${actionBean.mhnbgn.kekal ne 'Y' && actionBean.mhnbgn.kekal ne 'T'}">
                        <input type="radio" checked="checked" name="kekal" id="kekal" value="kekal"/>Kekal
                        <input type="radio" name="kekal" id="xkekal" value="provisional"/>Sementara
                    </c:if>
                    <c:if test="${actionBean.mhnbgn.kekal eq 'T'}">
                        <input type="radio"  name="kekal" id="kekal" value="kekal"/>Kekal
                        <input type="radio" name="kekal" checked="checked" id="xkekal" value="provisional"/>Sementara
                    </c:if>
                </p>
                <c:if test="${actionBean.mhnbgn.lain eq null}">
                    <p>
                        <label><font color="red"><em>*</em></font>Menara : </label>
                        <input type="radio" name="menara"  id="menara" value="y" onclick="menara2(this.value);"/>Ya
                        <input type="radio" name="menara" checked="checked" id="xmenara" value="x" onclick="menara2(this.value);"/>Tidak
                    </p>
                    <div id="senaraimenara">
                        <p>
                            <label><font color="red"><em>*</em></font>Bilangan Menara : </label>
                            <s:text name="bilmenara" id="bilmenara" size="4"/>
                        </p>
                        <p>
                            <label><font color="red"><em>*</em></font>Nama Menara : </label>
                            <s:text name="namamenara" id="namamenara" size="30"/>
                        </p>
                    </div>
                </c:if>
                <c:if test="${actionBean.mhnbgn.lain ne null}">
                    <p>
                        <label><font color="red"><em>*</em></font>Menara : </label>
                        <input type="radio" name="menara"  checked="checked" id="menara" value="y" onclick="menara2(this.value);"/>Ya
                        <input type="radio" name="menara" id="xmenara" value="x" onclick="menara2(this.value);"/>Tidak
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Bilangan Menara : </label>
                        <s:text name="bilmenara" id="bilmenara" size="4" value="${actionBean.mhnbgn.bilanganMenara}"/>
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Nama Menara : </label>
                        <s:text name="namamenara" id="namamenara" size="30" value="${actionBean.mhnbgn.lain}"/>
                    </p>
                </c:if>
                <p>
                    <label><font color="red"><em>*</em></font>Kegunaan Bangunan : </label>
                    <s:select name="kodKegunaanBangunan" value="${actionBean.mhnbgn.kodKegunaanBangunan.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p><label><font color="red"><em>*</em></font>Jumlah Unit Syer Bagi Bangunan ${actionBean.mhnbgn.nama}: </label>
                    <s:text name="unitSyer" id="unitSyer" size="10" maxlength = "7" value="${actionBean.mhnbgn.syerBlok}" onblur="javascript:nonNumber(this, this.value);"/>
                </p>
                <%--<p><label><font color="red"><em>*</em></font>Jumlah Tingkat : </label>
                    <s:text name="biltingkat" id="biltingkat" size="4" value="${actionBean.mhnbgn.bilanganTingkat}" onblur="javascript:nonNumber(this, this.value);"/>
                </p>--%>

                <center>
                    <%--<s:button class="btn" value="Kemaskini" name="kemaskini" onclick="pilihjenis(this.name, this.form);"/>--%>
                    <s:button class="longbtn" value="Simpan Bangunan" name="kemaskini1" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </center>
                <p></p>
                <p></p>
            </fieldset>
            <p>
                <%--</c:if>--%>
                <%-- <c:if test="${petak}">
                     <fieldset>
                         <font color="blue"><b>Maklumat Tingkat</b></font>
                         <p>
                             <label>Bawah Tanah :</label>
                             <input type="radio"  name="bawahTanah" id="bawahTanah" value="bawahTanah"/>Ya
                             <input type="radio" name="bawahTanah" checked="checked" id="bawahTanah" value="xbawahTanah"/>Tidak
                         </p>
                         <c:if test="${actionBean.bgnTingkat.bilanganPetak eq null || actionBean.bgnTingkat.bilanganPetak eq 0}">
                             <p>
                                 <label>Bilangan Petak :</label>
                                 <s:text name="petak" id="petak" size="4" value="${actionBean.bgnTingkat.bilanganPetak}" onblur="javascript:nonNumber(this, this.value);"/>
                             </p>
                         </c:if>
                         <c:if test="${actionBean.bgnTingkat.bilanganPetak ne null && actionBean.bgnTingkat.bilanganPetak ne 0}">
                             <p>
                                 <label>Bilangan Petak :</label>
                                 <s:text name="petak" id="petak" size="4" value="${actionBean.bgnTingkat.bilanganPetak}" readonly="true"/>
                             </p>
                         </c:if>
                         <c:if test="${empty actionBean.bngnnPetak}">
                             <p>
                                 <label><font color="red"><em>*</em></font>Petak Mula :</label>
                                 <s:text name="petakMula" id="petakMula" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                             </p>
                             <p>
                                 <label>Unit Syer :</label>
                                 <s:text name="unit_Syer" id="unit_Syer" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                             </p>
                             <p>
                                 <label>Luas (Meter Persegi) :</label>
                                 <s:text name="luas" id="luas" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                             </p>
                             <p>
                                 <label>Kegunaan Petak :</label>
                                 <s:select name="kodKegunaanPetak" id="kodKegunaanPetak">
                                     <s:option value="">Sila Pilih</s:option>
                                     <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                                 </s:select>
                             </p>
                             <center>
                                 <s:button class="longbtn" value="Kemaskini Tingkat" name="kemaskini3" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                 <p><font color="red">Arahan : Jika Maklumat Petak di bawah tidak mempunyai sebarang rekod, sila klik 'Kemaskini Tingkat' sekali lagi.</font></p>
                             </center>
                             <p></p>
                             <p></p>
                         </c:if>
                     </fieldset>
                     <p></p>
                     ${actionBean.SenaraiPetakAksesori}
                     <c:if test="${!empty actionBean.bngnnPetak}">
                         <center>

                        <display:table style="width:50%" class="tablecloth" name="${actionBean.bngnnPetak}" pagesize="100" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Petak">${line.nama}</display:column>
                            <display:column title="Kegunaan Petak">${line.kegunaan.nama}</display:column>
                            <display:column title="Luas (Meter Persegi)">${line.luas}</display:column>
                            <display:column title="Syer">${line.syer}</display:column>
                            <display:column title="Petak Aksesori">
                                <c:forEach items="${actionBean.senaraiPetakAksesoribyIdTgkt}" var="line2">
                                    <c:if test="${line.idPetak eq line2.petak.idPetak}">
                                        ${line2.nama}
                                    </c:if>
                                </c:forEach>
                            </display:column>
                        </display:table>

                    </center>

                    <center>
                        <s:hidden name="tingkat" id="tingkat" value="${actionBean.tingkat}"/>
                        <s:button class="longbtn" value="Hapus Data Tingkat" name="deleteTingkat" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </center>
                </c:if>
            </c:if>--%>

                <%--<c:if test="${!empty actionBean.petakMula && empty actionBean.bngnnPetak}">
                    <c:if test="${empty actionBean.bngnnPetak && actionBean.petakMula ne null && actionBean.bgnTingkat.bilanganPetak ne 0}">
                <fieldset>
                    <font color="blue"><b>Senarai Maklumat Petak</b></font>
                    <p><font color="red">Arahan : Sila klik 'Kemaskini Petak' sebelum klik 'Petak Aksesori' untuk memasukkan Petak Aksesori</font></p>
                    <table class="tablecloth" align="center">
                        <tr style="width: 100%"></tr>
                        <c:set value="0" var="count"/>
                        <th>Senarai Petak</th>
                        <th>Unit Syer</th>
                        <th>Luas (Meter Persegi)</th>
                        <th>Kegunaan Petak</th>
                        <th>Petak Aksesori</th>
                        <c:set value="1" var="count"/>
                        <c:forEach var="i" begin="1" end="${actionBean.petak}" >
                            <tr>
                                <td>
                                    <s:text name="bil" id="bil${actionBean.petakMula+count}" value="${actionBean.petakMula+count}" size="5" readonly="true"/>
                                </td>
                                <td><s:text name="unit_Syer" id="unit_Syer${actionBean.petakMula+count}" size="5" onblur="javascript:nonNumber(this, this.value);"/>
                                </td>
                                <td><s:text name="luas" id="luas${actionBean.petakMula+count}" size="5" onblur="javascript:nonNumber(this, this.value);"/>value="${actionBean.bngnnPetak.luas}"
                                </td>
                                <td><s:select name="kodKegunaanPetak" id="kodKegunaanPetak${actionBean.petakMula+count}"> value="${actionBean.bngnnPetak.kegunaan.kod}"
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                                    </s:select>
                                </td>
                                <td><s:button class="longbtn" name="petakAksesori" value="Petak Aksesori" id="petakAksesori${actionBean.petakMula+count}" onclick="popup('${actionBean.petakMula+count}');"/>
                                </td>
                            </tr>
                            <c:set value="${count +1}" var="count"/>
                        </c:forEach>
                    </table>
                    <center>
                        <s:button class="longbtn" value="Kemaskini Petak" name="kemaskini5" onclick="simpanPetakDetails(this.name, this.form);"/>
                    </center>
                </fieldset>
            </c:if>--%>
            </c:if>
        </c:if>
        <c:if test="${!empty actionBean.cekMhnBngn}">
            <c:if test="${actionBean.cekMhnBngn[0].syerBlok ne 0}">
                <br>
            <center><legend><font color="blue" size="3">Maklumat Bangunan</font></legend></center>
            <br>
            <table class="tablecloth" align="center">
                <tr style="width: 100%"></tr>
                <c:set value="0" var="count"/>
                <th>Nama Bangunan</th>
                <th>Kategori Bangunan</th>
                <th>Kegunaan Bangunan</th>
                <th>Jenis Bangunan</th>
                <th>Syer Blok</th>
                <th>Bilangan Tingkat</th>
                <th>Bilangan Petak</th>
                <%--<c:set value="1" var="count"/>--%>
                <c:forEach items="${actionBean.cekMhnBngn}" var="line">
                    <c:if test="${line.syerBlok ne 0}">

                        <tr>
                            <td>${line.nama} &nbsp;&nbsp;
                                <c:if test="${line.bilanganMenara ne 0}">(Menara ${line.lain})</c:if>
                            </td>
                            <td><c:if test="${line.kodKategoriBangunan.kod eq 'B'}">Bangunan</c:if>
                                <c:if test="${line.kodKategoriBangunan.kod eq 'L'}">Bangunan Tanah</c:if>
                                <c:if test="${line.kodKategoriBangunan.kod eq 'P'}">Sementara (Provisional)</c:if>
                            </td>
                            <td>${line.kodKegunaanBangunan.nama}</td>
                            <td><c:if test="${line.kekal eq 'Y'}">Kekal</c:if>
                                <c:if test="${line.kekal eq 'T'}">Sementara (Provisional)</c:if>
                            </td>
                            <td>${line.syerBlok}</td>
                            <td>${line.bilanganTingkat}</td>
                            <td>${line.bilanganPetak}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <br>






            <c:if test="${senaraitgkat}">
                <p>  </p><c:if test="${!empty actionBean.senaraiTgktbyBgnn}">

                    <label>Tingkat :</label>
                    <s:select name="tingkat" id="tingkat">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiTgktbyBgnn}" label="nama" value="nama"/>
                    </s:select>
                    <s:button class="btn" value="Cari" name="kemaskini4" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <br>

                </c:if>
                <br>
                <c:if test="${actionBean.mhnbgn.syerBlok ne 0}">
                    <center>
                        <s:button class="longbtn" value="Tambah Tingkat ${actionBean.mhnbgn.nama}" name="tambahTingkat" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </center>
                </c:if>
                <br>
                <p></p><c:if test="${!empty actionBean.senaraiTgktbyBgnn}">
                    <center><legend><font color="blue" size="3">Maklumat Tingkat</font></legend></center>
                    <br>
                    <table class="tablecloth" align="center">
                        <tr style="width: 100%"></tr>
                        <c:set value="0" var="count"/>
                        <th>Menara</th>
                        <th>Nama Tingkat</th>
                        <th>Tingkat Bawah / Tingkat</th>
                        <th>Bilangan Petak</th>
                        <th>Tingkat Mezanin</th>
                        <th>Tindakan</th>
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.senaraiTgktbyBgnn}" var="line">
                            <tr>
                                <td>${line.lain}</td>
                                <td>${line.nama}</td>
                                <td>${line.tingkat}</td>
                                <td>${line.bilanganPetak}</td>
                                <td>${line.mezanin}</td>
                                <td><div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='${line.tingkat}' onclick="hapusTingkat('${line.nama}',this.value,this.form);" onmouseover="this.style.cursor = 'pointer';" >
                                    </div>
                                </td>
                            </tr>
                            <c:set value="${count +1}" var="count"/>
                        </c:forEach>
                    </table>
                    <br>
                </c:if>

            </c:if>


            <c:if test="${tambhTgkt}">
                <br>
                <fieldset><legend>Kemasukan Tingkat Baharu </legend><br>

                    <p>
                        <label><font color="red"><em>*</em></font>Tingkat : </label>
                        <s:text name="namaTgkt" id="namaTgkt" size="30"/><em>(contoh: 1 @ 2 @ 1 DAN 2 @ DI LUAR BANGUNAN M1)</em>
                    </p>

                    <p>
                        <label>Berkongsi Tingkat :</label>
                        <input type="radio"  name="kongsiTgkt" id="kongsiTgkt" value="y" onclick="kongsiTgkt2(this.value)"/>Ya
                        <input type="radio" name="kongsiTgkt" checked="checked" id="xkongsiTgkt" value="t" onclick="kongsiTgkt2(this.value)"/>Tidak
                    </p>
                    <div id="tgktBwh">
                        <p>
                            <label><font color="red"><em>*</em></font>Tingkat Bawah : </label>
                            <s:text name="tgkt" id="tgkt" size="30"/><em>(contoh: Tingkat  = 1 DAN 2, Tingkat Bawah = 1)</em>
                        </p>
                    </div>


                    <p>
                        <label>Bawah Tanah :</label>
                        <input type="radio"  name="bawahTanah" id="bawahTanah" value="bawahTanah"/>Ya
                        <input type="radio" name="bawahTanah" checked="checked" id="bawahTanah" value="xbawahTanah"/>Tidak
                    </p>
                    <p>
                        <label><font color="red"></font>Menara (Jika Ada): </label>
                        <s:text name="menaraTgkt" id="mnamaMenaraTgkt" size="30"/><em>(contoh: A @ B @ A-C)</em>
                    </p>
                    <p>
                        <label>Mezanin :</label>
                        <input type="radio"  name="jenisMezanin" id="mezanin1" value="y" onclick="changeVal(this.value)"/>Ya
                        <input type="radio" name="jenisMezanin" checked="checked" id="mezanin1" value="t" onclick="changeVal(this.value)"/>Tidak
                    </p>
                    <div id="mezanin2">
                        <p>
                            <label><font color="red"><em>*</em></font>Nama Mezanin : </label>
                            <s:text name="mezanin" id="mezanin" size="7" maxlength="4"/>
                            <em>(contoh: N1)</em>
                        </p>
                    </div>
                    <p>
                        <label><font color="red"><em>*</em></font>Bilangan Petak : </label>
                        <s:text name="petakTgkt" id="petakTgkt" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Nombor Petak Mula: </label>
                        <s:text name="petakTgktMula" id="petakTgktMula" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p></p>
                    <p></p>
                    <p></p><center>-- Setiap Petak --</center>
                    <p>
                        <label><font color="red"><em>*</em></font>Kegunaan Petak: </label>
                        <s:select name="kodKegunaanPetak" id="kodKegunaanPetak">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Unit Syer : </label>
                        <s:text name="petakUnitSyer" id="petakUnitSyer" size="10" maxlength="6" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Luas Petak (meter persegi) : </label>
                        <s:text name="petakLuas" id="petakLuas" size="10" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <%--<p>
                        <label>Bilangan Pelan Lebih Dari 1 :</label>
                        <input type="radio"  name="pelanByk" id="pelanByk1" value="y" onclick="pelanByk2(this.value)"/>Ya
                        <input type="radio" name="pelanByk" checked="checked" id="pelanByk1" value="t" onclick="pelanByk2(this.value)"/>Tidak
                    </p>
                    <div id="bilPelanByk">
                        <p>
                            <label><font color="red"><em>*</em></font>Bilangan Pelan  : </label>
                            <s:text name="bilPelan" size="7" maxlength="10"/>&nbsp;&nbsp;
                        </p>

                        <p>
                            <label><font color="red"><em>*</em></font>Petak Terlibat  : </label>
                            <s:text name="petakTerlibat" size="7" maxlength="10"/>
                        </p>
                        <p>
                            <label><font color="red"><em>*</em></font>No. Pelan  : </label>
                            PA(B)<s:text name="pelanPetak" id="pelanPetak" size="40" />
                        </p>
                    </div>
                    <div id="bilPelan1">
                        <p>
                            <label><font color="red"><em>*</em></font>No. Pelan  : </label>
                            PA(B)<s:text name="pelanPetak" id="pelanPetak" size="40" />
                        </p>
                    </div>--%>

                    <center>
                        <s:button class="longbtn" value="Simpan Tingkat" name="simpanTingkat" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </center>
                </fieldset>
            </c:if>


            <c:if test="${petak}">
                <center>
                    <p>
                        <font color="red">Sila tekan butang 'Simpan' jika ada perubahan.</font>
                    </p>
                    <c:if test="${!empty actionBean.bngnnPetak}">
                        <s:hidden name="idtgkt" id="idtgkt" value="${actionBean.bngnnPetak[0].tingkat.idTingkat}"/>
                    </c:if>
                    <c:set value="0" var="count"/>
                    <display:table style="width:40%" class="tablecloth" name="${actionBean.bngnnPetak}" pagesize="100" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Petak"><s:text name="petak${count}" id="petak${count}" value="${line.nama}" readonly="true"/></display:column>
                        <display:column title="Kegunaan Petak">
                            <s:select name="kodKegunaanPetak${count}" id="kodKegunaanPetak${count}" value="${line.kegunaan.kod}" onchange="enableSimpan2('${count}',this, this.value);">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Luas (Meter Persegi)"><s:text name="luasEdit${count}" id="luasEdit${count}" value="${line.luas}" onchange="enableSimpan2('${count}',this, this.value);"/></display:column>
                        <display:column title="Syer"><s:text name="syerEdit${count}" id="syerEdit${count}" value="${line.syer}" onchange="enableSimpan2('${count}',this, this.value);"/></display:column>
                        <%-- <display:column title="No Pelan (PA(B))">
                             <c:if test="${!empty line.pabPetak}">
                                 <s:text name="pabPetakEdit${count}" id="pabPetakEdit${count}" value="${line.pabPetak}" onchange="enableSimpan2('${count}',this, this.value);"/>
                             </c:if>
                             <c:if test="${empty line.pabPetak}">
                                 <s:text name="pabPetakEdit${count}" id="pabPetakEdit${count}" value="" onchange="enableSimpan2('${count}',this, this.value);"/>
                             </c:if>
                         </display:column>--%>
                        <%--<display:column title="Petak Aksesori">
                            <c:forEach items="${line.senaraiPetakAksesori}" var="line2">
                                    A${line2.nama}
                            </c:forEach>
                        </display:column>--%>
                        <display:column title="Tindakan">
                            <s:button class="longbtn" name="kemaskini5" value="Simpan" id="simpanPetakEdit2${count}" onclick="simpanPetakDetails('${line.nama}','${line.idPetak}','${count}',this.value,this.form);"/>
                            <s:button class="longbtn" name="petakAksesori2" value="Petak Aksesori" onclick="popup('${line.nama}','${line.idPetak}');"/>
                            <s:button class="longbtn" name="hapusPetak" value="Hapus" id="hapus" onclick="hapusPetak2('${line.nama}','${line.idPetak}',this.value,this.form);"/>
                        </display:column>
                        <c:set value="${count +1}" var="count"/>
                    </display:table>
                    <p></p><br>
                    <center>
                        <s:button class="longbtn" value="Tambah Petak" name="tambahPetak" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </center>
                </center>
            </c:if>

            <c:if test="${tambahPetak}">
                <br>
                <fieldset> Tambah petak
                    <p>
                        <label><font color="red"><em>*</em></font>Bilangan Petak : </label>
                        <s:text name="petakTgktTambah" id="petakTgktTambah" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Nombor Petak Mula: </label>
                        <s:text name="petakTgktMulaTambah" id="petakTgktMulaTambah" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p></p>
                    <p></p>
                    <p></p><center>-- Setiap Petak --</center>
                    <p>
                        <label><font color="red"><em>*</em></font>Kegunaan Petak: </label>
                        <s:select name="kodKegunaanPetak" id="kodKegunaanPetak">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Unit Syer : </label>
                        <s:text name="petakUnitSyerTambah" id="petakUnitSyerTambah" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                    <p>
                        <label><font color="red"><em>*</em></font>Luas Petak (meter persegi) : </label>
                        <s:text name="petakLuasTambah" id="petakLuasTambah" size="4" onblur="javascript:nonNumber(this, this.value);"/>
                    </p>
                </fieldset>
                <br>
                <center>
                    <s:button class="longbtn" value="Simpan Petak" name="simpanPetak" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </center>
            </c:if>

            <%--<c:if test="${!empty actionBean.bngnnPetak}">
                <center>

                <display:table style="width:50%" class="tablecloth" name="${actionBean.bngnnPetak}" pagesize="100" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Petak">${line.nama}</display:column>
                    <display:column title="Kegunaan Petak">${line.kegunaan.nama}</display:column>
                    <display:column title="Luas (Meter Persegi)">${line.luas}</display:column>
                    <display:column title="Syer">${line.syer}</display:column>
                    <display:column title="Petak Aksesori">
                        <c:forEach items="${actionBean.senaraiPetakAksesoribyIdTgkt}" var="line2">
                            <c:if test="${line.idPetak eq line2.petak.idPetak}">
                                ${line2.nama}
                            </c:if>
                        </c:forEach>
                    </display:column>
                </display:table>

            </center>
        </c:if>--%>

        </c:if>
    </c:if>
</s:form>
