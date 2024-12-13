<%-- 
    Document   : syor_pulangan_wang_cagaran
    Created on : Jun 8, 2011, 11:20:39 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>



<script type="text/javascript">
    function save1(event, f){
          
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
                    
        },'html');
            
    }
    
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.SyorPulangWangCagar" name="form" id="form">
    <s:messages/>
    <s:errors/>

    <%--nak edit --%>
    <div class="subtitle" style="text-transform: capitalize" >
        <fieldset class="aras1">
            <legend> </legend>
            <div class="content">

                <table border="0" width="80%" cellspacing="10%" align="center">
                    <tr><td id="tdLabel" ><b><font style="text-transform: capitalize">
                                    <tr><td>
                                            <p><b><div style="text-transform:uppercase;text-align: center"> SYOR PULANGAN ATAU RAMPAS WANG CAGARAN1</div></b></p>
                                             <%--Value lama ${actionbean.tajuk} --%>
                                        </td></tr></font></b></td></tr>
                </table>
                <c:if test="${actionBean.stageId eq 'sedia_laporan_pantau'}">                          
                <table border="0" cellspacing="0" align="center">

                    <tr align="center"><td>1<s:textarea name="syorUlasan" class="normal_text" rows="5" cols="150"/></td></tr>

                </table>
                </c:if>
                <c:if test="${!(actionBean.stageId eq 'sedia_laporan_pantau')}">                          
                <table border="0" cellspacing="0" align="center">

                    <tr align="center"><td>Ulasan :&nbsp;&nbsp;${actionBean.syorUlasan}</td></tr>

                </table>
                </c:if>
                <c:if test="${actionBean.stageId eq 'sedia_laporan_pantau'}">
                <p align="center">
                    1<s:button name="SimpanPulangCagar" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                </c:if>
            </div>
        </fieldset>
    </div>



</s:form>