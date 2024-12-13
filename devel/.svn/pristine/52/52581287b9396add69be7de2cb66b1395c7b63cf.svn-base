<%-- 
    Document   : setup_denda_tahunan
    Created on : Apr 25, 2011, 7:29:54 PM
    Author     : abdul.hakim
--%>

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
    function janaCukai(){
        alert("Cukai Tahunan telah berjaya dijana");
    }
    
    function doSubmit(f){
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

    function janaDenda(f){
        var kodNegeri = document.getElementById('negeri');
        var form = $(f).formSerialize();
        var report =null;
        var kodCawangan = document.getElementById('daerah');

        if(kodNegeri.value == 'melaka'){
            report = 'HSL_R_60_MLK.rdf';
        }else{
            report = 'HSL_R_60_NS.rdf';
        }
        var url = "reportName="+report+"%26report_p_kod_caw="+kodCawangan.value;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
    
    function janaDendaStrata(f){
        var kodNegeri = document.getElementById('negeri');
        var form = $(f).formSerialize();
        var report =null;
        var kodCawangan = document.getElementById('daerah');

        if(kodNegeri.value == 'melaka'){
            report = 'HSL_R_60S_MLK.rdf';
        }else{
            report = 'HSL_R_60_NS.rdf';
        }
        var url = "reportName="+report+"%26report_p_kod_caw="+kodCawangan.value;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

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
    
    function checking(){
        var kodCawangan = document.getElementById('daerah');
        $.get("${pageContext.request.contextPath}/hasil/denda_tahunan?getTransaction&kodCawangan="+kodCawangan.value,
        function(data){
            if(data == 'O'){
                return true;
            }else{
                alert("Denda telah dijana pada "+data);
                $('#denda').attr("disabled", "true");
                $('#cetak').show();
                $('#cetakStrata').show();
                return false;
            }
        });
    }
    
    function checkCawangan(caw){
        $.get("${pageContext.request.contextPath}/hasil/denda_tahunan?getTransaction&kodCawangan="+caw,
        function(data){
            if(data == 'O'){
                $('#denda').removeAttr("disabled");
                $('#cetak').hide();
                $('#cetakStrata').hide();
                $('#denda').show();
                return true;
            }else{
                alert("Denda telah dijana pada "+data);
                $('#denda').attr("disabled", "true");
                $('#cetak').show();
                $('#cetakStrata').show();
                return false;
            }
        });
    }
</script>
<script language="javascript" >
    $(document).ready(function() {
        $('#cetak').hide();
        $('#cetakStrata').hide();
        $('#denda').hide();
    });
</script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Set-up Denda Lewat</font>
            </div>
        </td>
    </tr>
</table></div>
<stripes:errors />
<stripes:messages/>
<p></p>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<stripes:form beanclass="etanah.view.stripes.hasil.DendaTahunanActionBean" id="denda_tahunan" >
    <stripes:text name="kodNegeri" value="${actionBean.kodNegeri}" id="negeri" style="display:none;"/>
     <div class="subtitle">
         <fieldset class="aras1">
             <legend>Setup Denda Lewat</legend>
             <p></p>
             <p>&nbsp;&nbsp;
                 <em style="font-size:20px"><b>Perhatian : </b></em><font color="red">Penjanaan Denda Tahunan dilakukan pada 31 Mei pada setiap tahun.<br>
                 &nbsp;&nbsp;&nbsp
                 Sila pastikan data dikemaskini, laporan dijana dan backup dilakukan sebelum setup denda.</font><br>
                 &nbsp;&nbsp;&nbsp
                 Sila klik butang Jana untuk menjana Denda Lewat<br>
                 <label><em>*</em>Pilih Cawangan :</label>
                 <stripes:select name="cawanganDenda" id="daerah" onchange="checkCawangan(this.value);">
                     <stripes:option label="Pilih Cawangan ..." value="" />
                     <c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                         <stripes:option value="${i.kod}">${i.name}</stripes:option>
                     </c:forEach>
                 </stripes:select>
             </p>
             <p>
                 <label>&nbsp;</label>
                 <stripes:submit name="generateDenda" class="btn" value="Jana Landed" id="denda" onclick="doSubmit(this.form);"/>&nbsp;&nbsp;
                 <stripes:submit name="generateDendaStrata" class="btn" value="Jana Strata" id="denda" onclick="doSubmit(this.form);"/>
                 <!--stripes:button name="generateDenda" class="btn" value="Jana" id="denda" onclick="return checking()"/-->
                 <stripes:button name=" " id="cetak" onclick="janaDenda(this.form);" value="Cetak" class="btn"/>&nbsp;
                 <stripes:button name=" " id="cetakStrata" onclick="janaDendaStrata(this.form);" value="Cetak Strata" class="btn"/>&nbsp;
             </p>
             <p></p>
             <p></p>
         </fieldset>
     </div>

     <p></p>

    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>Setup Archive</legend>
            <p></p>
            <p>&nbsp;&nbsp;&nbsp
                Sila klik butang Jana untuk menjana Archive<br>
                <label><em>*</em>Pilih Daerah :</label>
                <stripes:select name="cawanganAchive" id="daerah">
                    <stripes:option label="Pilih Daerah ..." value="" />
                    <c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                        <stripes:option value="${i.kod}">${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:button name="" onclick="generateReportArchive(this.form)" value="Jana Report" class="btn"/>
                <stripes:submit name="generateArchive" class="btn" value="Jana" id="archive" />
            </p>

            <p></p>
            <p></p>
        </fieldset>
    </div>

     <p></p>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Setup Cukai Tahunan</legend>
            <p></p>
            <p>&nbsp;&nbsp;&nbsp
                Sila klik butang Jana untuk menjana Cukai Tahunan<br>
                <label><em>*</em>Pilih Daerah :</label>
                <stripes:select name="cawanganCukai" id="daerah">
                    <stripes:option label="Pilih Daerah ..." value="" />
                    <c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                        <stripes:option value="${i.kod}">${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <stripes:submit name="generateCukai" class="btn" value="Jana" id="cukai" />
            </p>

            <p></p>
            <p></p>
        </fieldset>
    </div>--%>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="right">&nbsp;
                    <%--<stripes:submit name="Step3" value="Seterusnya" class="btn" id="btn"/>--%>
                </div>
            </td>
        </tr>
    </table></div>
</stripes:form>