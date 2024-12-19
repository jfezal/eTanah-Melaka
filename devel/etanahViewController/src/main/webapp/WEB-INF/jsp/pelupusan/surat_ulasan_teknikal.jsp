<%--
    Document   : surat_ulasan_teknikal
    Created on : May 26, 2010, 11:23:50 AM
    Author     : nurul.izza
--%>

<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>


<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<head>--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perihal Permohonan</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>


<%--<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
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
<script language="javascript" type="text/javascript"
src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>

    <script language="javascript" type="text/javascript"
    src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>

    <script language="javascript" type="text/javascript"
    src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

    <script type="text/javascript"
    src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>--%>

<script type="text/javascript">
    $(document).ready(function() {
        maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

    });

    function reset1(){
    <%--alert("reset");--%>
            document.getElementById("namaPenyedia").value ="";
            document.getElementById("noRujukan").value ="";
            document.getElementById("ulasan").value ="";
            document.getElementById("syorJabatan1").value ="";
        }

        function validation(){
            if($("#syorJabatan1").val() == "0"){
                alert('Sila Pilih " SyorJabatan " .');
                $("#syorJabatan1").focus();
                return true;
            }
            if($("#ulasan").val() == ""){
                alert('Sila masukkan " ulasan " terlebih dahulu.');
                $("#ulasan").focus();
                return true;
            }
            return false;
        }

        function saveJabatanTeknikal(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
    <%--self.close();--%>
                },'html');
            }
        }

        function addRow(idRujukan,index,kod)
        {
            var url = '${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?tambahRowjtek&idRujukan='+idRujukan+'&index='+index+ '&kod='+kod;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function deleteRow(idRujukan,index,kod)
        {
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {

                var url = '${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?deleteRowjtek&idRujukan='+idRujukan+'&index='+index+ '&kod='+kod;
                window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            }

        }
        function simpanJ(idRujukan, i, index,kod) {
            var kandungan5 = $('#kandungan5'+i).val();

            var url = '${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?simpanjtek&idRujukan='+idRujukan+'&kandungan5='+kandungan5+'&index='+index+ '&kod='+kod;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    <%--alert('Maklumat Telah Berjaya Disimpan');--%>
        }

        function simpanAll() {
            var len = $(".kandungan").length;

            for (var i=1; i<=len; i++) {
                var kand = ('.kandungan'+i);
                var kandungan5 = $('#kandungan5'+i).val();
                var id = $('#idRuj'+i).val();
                var agensi = $('#agensi'+i).val();
                var kod = $('#katAgensi'+i).val();
                var tarikhTerima = $('#tarikhTerima').val();
                var namaPenyedia = $('#namaPenyedia').val();
                var noRujukan = $('#noRujukan').val();
                var tarikhKuatkuasa = $('#tarikhKuatkuasa').val();
                var syorJabatan1 = $('#syorJabatan1').val();

                var url = '${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?simpanAll&kandungan5='+kandungan5 + '&id=' +id+'&agensi='+agensi+'&kod='+kod+
                    '&tarikhTerima='+tarikhTerima+'&namaPenyedia='+namaPenyedia+'&noRujukan='+noRujukan+'&tarikhKuatkuasa='+tarikhKuatkuasa+'&syorJabatan1='+syorJabatan1;
                window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            }

            <%--function refreshpage() {
                alert('aa');
                NoPrompt();
            }--%>
        }

</script>

<%--</head>--%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--<s:form beanclass="etanah.view.stripes.pelupusan.SuratUlasanTeknikalActionBean">--%>
<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikal2ActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Penyediaan Laporan
            </legend>
            <table>
                <s:hidden name="mohonRujLuar.tarikhJangkaTerima"/>
                <tr>
                    <td><label>Ulasan Daripada :</label>
                    <td>${actionBean.mohonRujLuar.namaAgensi}
                        <s:hidden name="mohonRujLuar.namaAgensi" />
                        <s:hidden name="mohonRujLuar.idRujukan" /></td>

                </tr>
                <tr>
                    <td><label>Tarikh Penyediaan :</label></td>
                    <td><fmt:formatDate value="${actionBean.mohonRujLuar.tarikhRujukan}" pattern="dd/MM/yyyy"/>
                        <s:hidden name="mohonRujLuar.tarikhRujukan" />
                </tr>
                <tr>
                    <td><label>Tarikh Terima :</label></td>
                    <td>
                        <s:text name="mohonRujLuar.tarikhTerima" id="tarikhTerima" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </tr>
                <tr>
                    <td> <label>Nama Penyedia :</label> </td>
                    <td><s:text name="mohonRujLuar.namaPenyedia" size="40" id="namaPenyedia" onkeyup="this.value=this.value.toUpperCase();"/></td>
                </tr>
                <tr>
                    <td><label>Nombor Rujukan / Surat Bilangan :</label>
                    <td><s:text name="mohonRujLuar.noRujukan" size="40" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();" />
                </tr>
                <tr>
                    <td> <label>Tarikh Surat :</label> </td>
                    <td><s:text name="mohonRujLuar.tarikhKuatkuasa" id="tarikhKuatkuasa" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan Jabatan Teknikal
            </legend>
            <table>
                <tr>
                    <td><label><font color="red">*</font>Syor Jabatan :</label>
                    <td><s:select name="mohonRujLuar.diSokong" id="syorJabatan1">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="1">Boleh Diluluskan</s:option>
                            <s:option value="2">Tidak Diluluskan</s:option>
                            <s:option value="3">Tiada Halangan</s:option>
                            <s:option value="4">Sesuai</s:option>
                            <s:option value="5">Tidak Sesuai</s:option>
                            <s:option value="6">Sokong</s:option>
                            <s:option value="7">Tidak Disokong</s:option>
                            <s:option value="8">Tidak Terima Ulasan</s:option>
                            <s:option value="9">Tidak Berkaitan</s:option>
                            <%--<s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>--%>
                        </s:select> </td>
                </tr>
                <tr>
                    <td valign="top"><label><font color="red">*</font>Ulasan :</label></td>
                    <%--<td><s:textarea name="mohonRujLuar.ulasan" rows="15" cols="50" id="ulasan"/> </td>--%>
                </tr>

                <c:set var="i" value="1" />
                <c:set var="num" value="1"/>
                <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikalKod}" var="line">
                    <tr>
                        <td>
                            <ol type="I" start="${num}">
                                <li></li>
                            </ol>
                        </td>
                        <td><font style="font-weight:bold;">${line.namaAgensi}</font></td>
                    </tr>
                    <tr>
                        <td style="text-align: right"><div id="res"></div></td>
                        <td>
                            <s:textarea  id="kandungan5${i}" name="senaraiUlasanJabatanTeknikalKod[${i-1}].ulasan" cols="80"  rows="5" class="kandungan"/>
                            <s:hidden id="${line.idRujukan}kandunganKertas" name="${line.idRujukan}kandunganKertas"/>
                            <s:hidden name="idRujukan" id="idRujukan" value="${line.agensi.kod}"/>
                            <s:hidden id="idRuj${i}" name="idRuj" value="${line.idRujukan}"/>
                            <s:hidden id="agensi${i}" name="idRuj" value="${line.agensi.kod}"/>
                            <s:hidden id="katAgensi${i}" name="idRuj" value="${line.agensi.kategoriAgensi.kod}"/>
                        </td>
                        <td style="vertical-align: middle;">
                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('${line.idRujukan}','${line.agensi.kod}','${line.agensi.kategoriAgensi.kod}')"></s:button><br>
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('${line.idRujukan}','${line.agensi.kod}','${line.agensi.kategoriAgensi.kod}')"/><br>
                            <%--<s:button name="simpanJtek" value="Simpan Ulasan" class="btn" onclick="simpanJ('${line.idRujukan}','${i}',${line.agensi.kod},'${line.agensi.kategoriAgensi.kod}')"/>--%>
                        </td>
                    </tr>

                    <c:set var="i" value="${i+1}" />
                    <c:set var="num" value="${num+1}"/>
                </c:forEach>

                <br>
                <tr>
                    <%--<td><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>  </td>--%>
                    <%--<s:button name="simpanPemohonPopup" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>--%>

                    <td></td>
                    <%--saveJabatanTeknikal(this.name, this.form);--%>
                    <td align="center"><s:button name="simpanJabatanTeknikal" id="save" value="Simpan" class="btn" onclick="simpanAll();"/>
                        <s:button name="reset" value="Isi Semula" class="btn" onclick="reset1();" />
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </td>
                </tr>

            </table>
        </fieldset>
    </div>

</s:form>