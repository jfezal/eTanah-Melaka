<%-- 
    Document   : kertasRisalatPerakuanPTD
    Created on : Jun 4, 2013, 9:26:43 AM
    Author     : khadijah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perakuan Pentadbir Tanah</title>
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
<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

$(document).ready(function() {

    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GRN'}">
            $('#jikaPajakan').hide();
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
            $('#jikaPajakan').show();
        </c:when>
        <c:otherwise>
            $('#jikaPajakan').hide();
        </c:otherwise>
    </c:choose>
    <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru ne null}">
            filterKodGunaTanah();
    </c:if>
    <c:if test="${actionBean.fasaPermohonan.keputusan ne null}">
        doFunc($('#ksn').val());
    </c:if>
     <c:if test="${actionBean.hakmilikPermohonan.kadarPremium ne 0}">
         $('#amnt').val(CurrencyFormatted(${actionBean.hakmilikPermohonan.kadarPremium}));
     </c:if>
    <c:choose>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">
            $('#syarat').show();
        </c:when>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">
            $('#syarat').hide();
        </c:when>
        <c:otherwise>
            $('#syarat').hide();
        </c:otherwise>
    </c:choose>
    }); //END OF READY FUNCTION

    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/hasil/kertas_risalat?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addRow(index,f)
    {
        NoPrompt();
//        var kodksn= document.getElementById("ksn").value;
//            var kpsn = '';
//            var ks = document.form.ksn;
//            for(var i=0; i< ks.length; i++){
//                if(ks[i].checked){
//                    kpsn = ks[i].value;
//
//                }
//            }
//
//            if(kpsn != null){
//                $('#syorKpsn').val(kpsn);
//                $('#ksn').val(kpsn);
//            }
//        var url = '${pageContext.request.contextPath}/hasil/kertas_risalat?tambahRow&index='+index+'&kodksn='+kodksn+'&ksn='+kpsn;
var url = '${pageContext.request.contextPath}/hasil/kertas_risalat?tambahRow&index='+index;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function deleteRow(idKandungan,f,tName)
    {
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/hasil/kertas_risalat?deleteRow&idKandungan='+idKandungan+'&tName='+tName+'&typeName=kPerakuanPtd',q,
            function(data){
                $('#page_div').html(data);

            }, 'html');
            self.refreshpage2('kPerakuanPtd') ;
        }
    }

    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2KertasMMKHasil('main');
        self.close();
    }

    function setKandungan(i,idKandungan){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        $('#'+idKandungan+'kandunganKertas').val(kandungan);
    }

    function cariPopup(val){
            var idMh = $('#idMh').val();
//            alert(idMh);
            NoPrompt();
            window.open("${pageContext.request.contextPath}/hasil/kertas_risalat?searchSyarat&jenisSyarat="+val+"&idMh="+idMh, "eTanah",
            "status=1,toolbar=0,location=1,menubar=0,width=910,height=800");
        }


        function doSomething(val){

            if(val=='GRN'|| val=='GM'){
                //alert(val);
                $('#jikaPajakan').hide();
            }else if (val=='PN' || val=='PM'){
                //alert(val);
                $('#jikaPajakan').show();
            }

        }

        function CurrencyFormatted(amount)
        {
            var i = parseFloat(amount);
            if(isNaN(i)) { i = 0.00; }
            var minus = '';
            if(i < 0) { minus = '-'; }
            i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if(s.indexOf('.') < 0) { s += '.00'; }
            if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
            s = minus + s;
            return s;
        }

        function filterKodGunaTanah() {
            var katTanah = $("#_kodKatTanah").val();
            //        alert("hi");
            NoPrompt();

            $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
            function(data) {
                if (data != '') {
                    $('#partialKodGunaTanah').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }

        function kiraCukai(f) {
            var kodTanah = $("#kodTanah").val();
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/hasil/kertas_risalat?kiraCukai&kodTanah=' + kodTanah , q,
            function(data) {
                if (data == '-1') {
                } else {
                    $('#cukai').val("RM "+CurrencyFormatted(data)+ " sehektar");
                }
            }, 'html');
        }

        function checkKandungan(i){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        if(kandungan == 'Sila Tambah Ayat Di Sini'){
            $('#kandungan5'+index).val("");
        }
    }
    function doFunc(val){

            $('#syarat').hide();
            setKpsn2(val);


    }

    function doFunc1(val){
            $('#syarat').show();
            setKpsn2(val);

    }

    function setKpsn2(val){
            $('#ksn').val(val);
            $('#syorKpsn').val(val);
        }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;

        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.hasil.KertasRisalatMMKNActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perakuan Pentadbir Tanah</legend>
                <table class="tablecloth" border="0" align="center">
                  
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerakuanPtd}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="6.${num}"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiKandunganPerakuanPtd[${i-1}].kandungan" cols="80"  rows="5" onclick="checkKandungan(${i})" onblur="setKandungan(${i},${line.idKandungan})" class="normal_text"/>
                                <s:hidden id="${line.idKandungan}kandunganKertas" name="${line.idKandungan}kandunganKertas"/>
                                <s:hidden name="idKandungan1" id="idKandungan1" value="${line.idKandungan}"/>
                            </td>
                            <td style="vertical-align: middle;">
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form,'mohonKertasKandungan')"></s:button>
                                </td>
                            </tr>

                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                   <%--<tr id="syarat">
                        <td colspan="3">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <c:import url="syarat/syaratPBMT.jsp"></c:import>
                                </c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>--%>
                    <tr>
                        <td style="text-align:center;" colspan="3">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('5',this.form)"/>
                            <s:hidden name="indexKertas" id="indexKertas" value="5"/>
                            <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="NoPrompt();"/>

                            <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </s:form>
</body>

