<%-- 
    Document   : setup_cukai_tahunan
    Created on : Feb 2, 2011, 11:48:25 AM
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
<script language="javascript" >
    $(document).ready(function() {
        var flg = ${actionBean.flag};
        if(flg){$('#janaCukai').attr("disabled", "true");}
      });
</script>
<script type="text/javascript">
    function janaCukai(){
        alert("Cukai Tahunan telah berjaya dijana");
    }

    function janaDenda(){
        alert("Denda Lewat telah berjaya dijana");
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

</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Set-up Cukai Tahunan</font>
            </div>
        </td>
    </tr>
</table></div>
<stripes:errors />
<stripes:messages/>
<p></p>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<stripes:form beanclass="etanah.view.stripes.hasil.CukaiTahunanActionBean" id="cukai_tahunan" >
    <stripes:text name="kodNegeri" value="${actionBean.kodNegeri}" id="negeri" style="display:none;"/>
    <%-- <div class="subtitle">
         <fieldset class="aras1">
             <legend>Setup Denda Lewat</legend>
             <p></p>
             <p>&nbsp;&nbsp;&nbsp
                 Sila klik butang Jana untuk menjana Denda Lewat<br>
                 <label><em>*</em>Pilih Daerah :</label>
                 <stripes:select name="cawanganDenda" id="daerah">
                     <stripes:option label="Pilih Daerah ..." value="" />
                     <c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                         <stripes:option value="${i.kod}">${i.nama}</stripes:option>
                     </c:forEach>
                 </stripes:select>
             </p>
             <p>
                 <label>&nbsp;</label>
                 <stripes:submit name="generateDenda" class="btn" value="Jana" id="denda"/>
             </p>
             <p></p>
             <p></p>
         </fieldset>
     </div>

     <p></p>--%>
     
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
    </div>--%>

     <p></p>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Setup Cukai Tahunan</legend>
            <p></p>
            <p>&nbsp;&nbsp;
                <em style="font-size:20px"><b>Perhatian : </b></em><font color="red">Penjanaan Setup Cukai Tahunan dilakukan pada 31 Disember pada setiap tahun.<br>
                 &nbsp;&nbsp;&nbsp
                 Sila pastikan data dikemaskini / ditutup, laporan dijana dan backup dilakukan sebelum setup cukai tahunan.</font><br>
                 &nbsp;&nbsp;&nbsp
                Sila klik butang Jana untuk menjana Cukai Tahunan<br>
                <label><em>*</em>Untuk Tahun :</label>
                <stripes:select name="tahunSetup" id="tahun" style="width:100px;">
                    <c:set var="year" value="${actionBean.tahunSetup}"/>
                    <stripes:option value="${year }">${year } </stripes:option>
                    <stripes:option value="${year + 1}">${year + 1} </stripes:option>
                </stripes:select>
            </p>
            
            <p>
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
                <stripes:submit name="generateCukai" class="btn" value="Jana" id="janaCukai" onclick="doSubmit(this.form);"/>
            </p>

            <p></p>
            <p></p>
        </fieldset>
    </div>

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