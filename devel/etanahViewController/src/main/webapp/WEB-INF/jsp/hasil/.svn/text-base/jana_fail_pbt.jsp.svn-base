<%-- 
    Document   : jana_fail_pbt
    Created on : Apr 20, 2011, 4:28:23 PM
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
<script type="text/javascript">
    function dateValidation(value, id){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

    function dateValidate(value, id){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }
</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Jana Fail PBT</font>
            </div>
        </td>
    </tr>
</table></div>
<stripes:errors />
<stripes:messages/>
<p></p>
<stripes:form beanclass="etanah.view.stripes.hasil.JanaFailPBT" id="jana_fail" >
    <stripes:text name="kodNegeri" value="${actionBean.kodNegeri}" id="negeri" style="display:none;"/>
     <div class="subtitle">
         <fieldset class="aras1">
             <legend>Jana Fail Pihak Berkuasa Tempatan</legend>
             <p></p>
             <p>&nbsp;&nbsp;&nbsp
                 Sila klik butang Jana untuk menjana Fail<br>
                 <label><em>*</em>Pilih Pihak Berkuasa Tempatan :</label>
                 <stripes:select name="pbt" id="pbt">
                     <stripes:option label="Sila Pilih ..." value="" />
                     <c:forEach items="${actionBean.senaraiKodTransaksi}" var="i" >
                         <stripes:option value="${i.kod}">${i.nama}</stripes:option>
                     </c:forEach>
                 </stripes:select>
             </p>
             <p>
                 <label>Tarikh Mula :</label>
                 <stripes:text name="tarikhMula" id="start" class="datepicker" onchange="dateValidation(this.value,'start')"/>
             </p>
             <p>
                 <label>Tarikh Hingga :</label>
                 <stripes:text name="tarikhHingga" id="end" class="datepicker" onchange="dateValidation(this.value,'end')"/>
             </p>
             <p>
                 <label>&nbsp;</label>
                 <stripes:submit name="Step2" class="btn" value="Jana" id="denda"/>
                <stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>
             </p>
             <p></p>
             <p></p>
         </fieldset>
     </div>

     <p></p>
</stripes:form>