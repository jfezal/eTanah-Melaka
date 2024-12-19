<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $("#idKump").hide();
        $("#denda").hide();
        $("#denda1").hide();
        $("#denda2").hide();
    });
</script>
<script type="text/javascript">
    function generateReportArchive(f){

        var form = $(f).formSerialize();
        var kodNegeri = document.getElementById("negeri");
        var report = null;
        if(kodNegeri.value == "melaka"){
            report = 'HSL_R_40_MLK.rdf';
        }else{
            report = 'HSL_R_37.rdf';
        }
        var param = document.getElementById('cawanganAchive');

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_kod_caw="+param.value+"&report_p_kod_daerah="+param.value, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    
    function doSubmit(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
//        $.unblockUI();
    }

    function displayKumpulan(id){
        if(id == 'kum'){
            $("#idKump").show();
            $("#denda").show();
            $("#denda1").hide();
            $("#denda2").hide();
            $("#idDaerah").hide();
        }else{$("#idKump").hide();
            $("#kump").val('');
            $("#idDaerah").show('');
            $("#denda").hide();}
    }

    function popupKumpulan(){
        var url = '${pageContext.request.contextPath}/hasil/bil_cukai_strata?popupCarianKumpulan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=1");
    }

    function refreshKump(kump, d){
        $("#kump").val(kump);
        $("#daerah").val(d);
    }

    function validateForm(){
        var katg = document.getElementById('katg');
        var kum = document.getElementById('kump');
        var d = document.getElementById('daerah');

        if(katg.value == 'kum'){
            if((kum.value == '')&&(d.value=='')){
                alert('Medan yang bertanda * adalah mandatori.');
                return false;
            }else{return true;}
        }
        if(katg.value == 'ind'){
            if(d.value==''){
                alert('Medan yang bertanda * adalah mandatori.');
                return false;
            }else{
                doSubmit();
                return true;}
        }else{return false;}
    }

    function checkingDatabase(kodCaw){
        var tahun = (document.getElementById('tahun')).value;
         var jenisCukai = "S";
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?checkingData&kodCaw=" + kodCaw+"&tahun="+tahun+"&jenisCukai="+jenisCukai,
        function(data){
            if(data == '1'){
                alert("Bil Cukai bagi Cawangan ini telah dijana.");
                $("#denda").hide();
                $("#denda1").hide();
                $("#denda2").show();
            }
            else if(data =='0'){
                $("#denda").hide();
                $("#denda1").show();
                $("#denda2").hide();
            }
        });
    }
</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Set-up Bil Cukai Strata</font>
                </div>
            </td>
        </tr>
    </table></div>
    <stripes:errors />
    <stripes:messages/>
<p></p>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<stripes:form beanclass="etanah.view.stripes.hasil.BilCukaiStratactionBean" id="bil_tahunan" >
    <stripes:text name="kodNegeri" value="${actionBean.kodNegeri}" id="negeri" style="display:none;"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Setup Bil Cukai</legend>
            <p></p>
            <p>&nbsp;&nbsp;&nbsp
                Sila klik butang Jana untuk menjana Bil Cukai<br>
                <label><em>*</em>Untuk Tahun :</label>
                <stripes:select name="tahunHantar" id="tahun" style="width:100px;">
                    <c:set var="year" value="${actionBean.tahunHantar}"/>
                    <stripes:option value="${year }">${year } </stripes:option>
                    <stripes:option value="${year + 1}">${year + 1} </stripes:option>
                </stripes:select>
            </p>
            
            <p>
                <label><em>*</em>Kategori :</label>
                <stripes:select name="kategori" id="katg" onchange="displayKumpulan(this.value)">
                    <stripes:option label="Pilih Kategori ..." value="" />
                    <stripes:option label="Individu" value="ind" />
                    <stripes:option label="Kumpulan" value="kum" />
                </stripes:select>
            </p>

            <p id="idKump">
                <label><em>*</em>Id Kumpulan :</label>
                <stripes:text name="idKumpulan" id="kump"/>
                <img alt='Klik Untuk Cari Dasar' border='0' height="16" width="16" src='${pageContext.request.contextPath}/pub/images/search-icon.png' class='rem'
                                     id='search' onclick='popupKumpulan();return false;' onmouseover="this.style.cursor='pointer';" title="Carian Kumpulan.">
            </p>
            
            <p id="idDaerah">
                <label><em>*</em>Pilih Cawangan :</label>
                <stripes:select name="daerah" id="daerah" onchange="checkingDatabase(this.value)">
                    <stripes:option label="Pilih Cawangan ..." value="" />
                    <stripes:option label="PTG MELAKA" value="00" />
                    <stripes:option label="PTD MELAKA TENGAH" value="01" />
                    <stripes:option label="PTD JASIN" value="02" />
                    <stripes:option label="PTD ALOR GAJAH" value="03" />
                    <%--<c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                        <stripes:option value="${i.kod}">${i.nama}</stripes:option>
                    </c:forEach>--%>
                </stripes:select>
            </p>            
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step2" class="btn" value="Jana" id="denda" onclick="return validateForm();"/>
                <stripes:submit name="Step4" class="longbtn" value="Jana Kali Pertama" id="denda1" onclick="return validateForm();"/>
                <stripes:submit name="Step5" class="longbtn" value="Jana Kali Kedua" id="denda2" onclick="return validateForm();"/>
                <stripes:submit name="Step3" class="btn" value="Muat Turun" id="mt"/>
            </p>
            <p></p>
            <p></p>
        </fieldset>
    </div>

    <p></p>
</stripes:form>