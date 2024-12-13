<%--
    Document   : carian_no_serahan
    Created on : Sep 12, 2012, 6:00:21 PM
    Author     : Siti Fariza Hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>
<script language="javascript" type="text/javascript">

    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yyyy'});
    });

    function validateForm2(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan Id Permohonan yang dikehendaki");
            $('#idPermohonanCarian').focus();
            return false;
        }

        if(document.getElementById("noSerahan").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan No Serahan yang dikehendaki");
            $('#noSerahan').focus();
            return false;
        }
        return true;
    }

    function validateForm(){

        var tarikh = document.getElementById('tarikh');
        var jam = document.getElementById('jam');
        var minit = document.getElementById('minit');
        var ampm = document.getElementById('ampm');

        if(tarikh.value == "" ){
            alert("Sila isikan Tarikh terlebih dahulu");
            $('#tarikh').focus();
            return false;
        }
        if(jam.value == "" ){
            alert("Sila isikan masa laporan terlebih dahulu");
            $('#jam').focus();
            return false;
        }

        if (jam.value >= 13){
            alert("Sila masukkan bilangan 1 sampai 12 sahaja.");
            $('#jam').focus();
            return false;
        }

        if(minit.value == "" ){
            alert("Sila isikan masa laporan terlebih dahulu");
            $('#minit').focus();
            return false;
        }

        if (minit.value >= 59){
            alert("Sila masukkan bilangan 0 sampai 59 sahaja.");
            $('#minit').focus();
            return false;
        }

        if(ampm.value == "" ){
            alert("Sila pilih am atau pm pada bahagian masa laporan");
            $('#ampm').focus();
            return false;
        }

        return true;
    }

    function refreshNoSerahan(noPerserahan){
    <%--alert("refreshPageDiari");--%>
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_no_serahan?reload&noPerserahan='+noPerserahan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function findPegawaiPenyiasat() {
            var namaPenerima = $('#namaPenerima').val();
            alert(namaPenerima);
    <%--var kodJP = $('#kodJP').val();
    var noPengenalanPenerima = $('#noPengenalanPenerima').val();
    var jawatanPenerima = $('#jawatanPenerima').val();--%>
            
            var url = '${pageContext.request.contextPath}penguatkuasaan/utiliti_no_serahan?carian&namaPenerima='+namaPenerima;
            $.ajax({
                url:url,
                success:function(data){
                    $('#page_div').html(data);
                }
            });


        }

        function findPegawaiPenyiasat2(id){
            if(id != ""){
                $.get('${pageContext.request.contextPath}/penguatkuasaan/utiliti_no_serahan?findPengguna&id='+id,
                function(data){
                    $('#page_div').html(data);
                    alert(${actionBean.pegawaiPenyiasat.noPengenalan});
                }, 'html');

                   

            }

        }

        function reload (val) {
            doBlockUI();
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_no_serahan?carian&namaPenerima=' + val;
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                    doUnBlockUI();
                },
                success : function(data){
                    $('#page_div').html(data);
                    doUnBlockUI();
                }
            });
        }

        function findPegawaiP(frm, id){
            if(id != ""){

                var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_no_serahan?findPengguna&id='+id;
                frm.action = url;
                frm.submit();
            }
        }
        
        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
 
</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiNoSerahanActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <%--<s:text name="penyerahBarangOperasi.noPerserahan" />--%>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">


            <legend>Carian No Serahan</legend>
            <p><label for="idPermohonan"><em>*</em>Id Permohonan/ID Perserahan :</label>
                <input type="text" name="idPermohonan" id="idPermohonanCarian" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p><label for="noPerserahan"><em>*</em>No Serahan :</label>
                <input type="text" name="noPerserahan" id="noSerahan" onkeyup="this.value=this.value.toUpperCase();"/>
                <%-- <s:submit name="searchNoSerahan" value="Cari" class="btn"/>--%>
            </p>
            <p>
                <label></label>
                <s:submit name="searchNoSerahan" value="Cari" class="btn" onclick="return validateForm2()"/>
            </p>

            <legend>No Serahan</legend>
            <p>
                <label>Id Permohonan/ID Perserahan :</label>
                ${actionBean.permohonan.idPermohonan} &nbsp;
            </p>
            <p>
                <label>No Serahan :</label>
                ${actionBean.noPerserahan} &nbsp;
            </p>
            <p>
                <label>Nama Penyerah :</label>
                ${actionBean.penyerahBarangOperasi.pengguna.nama} &nbsp;
            </p>
            <p>
                <label>No Pengenalan :</label>
                ${actionBean.penyerahBarangOperasi.pengguna.noPengenalan} &nbsp;
            </p>
            <p>
                <label>Pekerjaan :</label>
                ${actionBean.penyerahBarangOperasi.pengguna.jawatan} &nbsp;
            </p>
            <p>
                <label>Tempat Kerja :</label>
                ${actionBean.penyerahBarangOperasi.pengguna.kodCawangan.alamat.alamat1} &nbsp;
            </p>

            <legend>Maklumat Penerima</legend>

            <p>
                <label>Pegawai Siasat :</label>
                <s:select name="namaPenerima" id="namaPenerima" onchange="findPegawaiP(this.form, this.value)">
                    <s:option value="">Sila Pilih</s:option>
                    <c:forEach items="${actionBean.senaraiPegawaiPenyiasat}" var="line">
                        <s:option value="${line.idPelaksanaWaran}">${line.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            <s:hidden name="idPermohonan"/>
            <p>
                <label>Nama :</label>
                ${actionBean.namaPenerima}&nbsp;
            </p>
            <p>
                <label>No pengenalan :</label>
                ${actionBean.noPengenalanPenerima}&nbsp;
            </p>
            <p>
                <label>Jawatan :</label>
                ${actionBean.jawatanPenerima}&nbsp;
            </p>
            <p>
                <label>Tarikh Serah :</label>
                <%--${actionBean.tarikhPerserahan}--%>
                <s:text name="tarikh" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikh" value="${actionBean.tarikhPerserahan}"/>&nbsp;
            </p>
            <p>
                <label>Masa Serah :</label>
                <%--to set value for AM/PM--%>
                <c:set var="waktu" value="${fn:substring(actionBean.tarikhPerserahan,11,13)}"/>
                <c:if test="${waktu > 11}"><c:set var="time" value="PM"/></c:if>
                <c:if test="${waktu <= 11}"><c:set var="time" value="AM"/></c:if>
                <%--to set value for hour--%>
                <fmt:formatDate var="hourVal" value="${actionBean.tarikhPerserahan}" pattern="hh" />
                <%--to set value for minute--%>
                <fmt:formatDate var="minuteVal" value="${actionBean.tarikhPerserahan}" pattern="mm" />

                <s:select name="jam" id="jam" value="${hourVal}">
                    <s:option value=""> Jam </s:option>
                    <c:forEach var="hour" begin="1" end="12">
                        <c:choose>
                            <c:when test="${hour > 9}"><s:option value="${hour}">${hour}</s:option></c:when>
                            <c:otherwise><s:option value="0${hour}">0${hour}</s:option></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </s:select>
                <s:select name="minit" id="minit" value="${minuteVal}">
                    <s:option value=""> Minit </s:option>
                    <c:forEach var="minute" begin="00" end="59" >
                        <c:choose>
                            <c:when test="${minute > 9}"><s:option value="${minute}">${minute}</s:option></c:when>
                            <c:otherwise><s:option value="0${minute}">0${minute}</s:option></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </s:select>
                <s:select name="ampm" id="ampm" value="${time}" style="width:75px">
                    <s:option value="">Pilih</s:option>
                    <s:option value="AM">PAGI</s:option>
                    <s:option value="PM">PETANG</s:option>
                </s:select>
            </p>
            <p>
                <label>Tempat Serah :</label>
                <%--${actionBean.tempatPerserahan}--%>
                <s:text name="tempatPerserahan" id="tempatPerserahan"/>&nbsp;
            </p>
            <s:hidden name="pegawaiPenyiasat.idPelaksanaWaran"/>
            <s:hidden name="namaPenerima" id="namaPenerima"/>
            <s:hidden name="noPengenalanPenerima" id="noPengenalanPenerima"/>
            <s:hidden name="kodJP" id="kodJP"/>
            <s:hidden name="noPerserahan" id="noPerserahanCarian"/>
            <s:hidden name="jawatanPenerima" id="jawatanPenerima"/>
            <br/>
            <p>
                <label></label>
                <s:submit name="simpanMaklumatPenerima" id="save" value="Simpan dan Jana" class="longbtn" onclick="return validateForm()"/>
                <s:button name="" value="Papar Dokumen" class="longbtn" onclick="doViewReport('${actionBean.idDokumen}');"/>
            </p>
            <s:hidden name="idDokumen"/>
        </fieldset>
    </div>

</s:form>

