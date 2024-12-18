<%-- 
    Document   : draf_warta_gsa
    Created on : May 18, 2012, 5:30:04 PM
    Author     : Navin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script>
    function val1()
    {   if($('#permohonanLaporanKawasan.tarikhTerimaWarta').val() == "")
        {   alert("Sila masukkan Tarikh Terima Warta.");
            return false;
        }
        if($('#permohonanLaporanKawasan.tarikhWarta').val() == "")
        {   alert("Sila masukkan Tarikh Warta.");
            return false;
        }
        if($('#permohonanLaporanKawasan.noWarta').val() == "")
        {   alert("Sila masukkan Nombor Warta.");
            return false;
        }
        return true;
    }
    function val2()
    {   if($('#MTR.TNW').val() == "")
        {   alert("Sila masukkan Tarikh Warta.");
            return false;
        }
        if($('#MTR.NW').val() == "")
        {   alert("Sila masukkan Nombor Warta.");
            return false;
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.DrafWartaGSAActionBean">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
        <legend>Maklumat Warta</legend>
        <div>
            <table border="0">                     
                <tr>                    
                    <c:if test="${edit}">
                        <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA' && actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">--%>
                         <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">  
                            <td><label><em>*</em>No. Warta :</label></td>
                            <td><s:text id="noWarta" name="noWarta" class="normal" size="20"/></td>
                        </c:if>
                        <%--<c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA' && actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">--%>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}"> 
                            <td><label><em>*</em>No. Warta :</label></td>
                            <%--<td><s:text name="permohonanLaporanKawasan.noWarta" size="20"/></td>--%>
                            <td><s:text name="nWarta" size="20" id="nWarta" class="normal"/></td>
                        </c:if>
                    </c:if>
                    <c:if test="${view}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                            <%--<td>${actionBean.NW}</td>--%>
                            <%--<td>${actionBean.permohonanLaporanKawasan.noWarta}</td>--%>
                            <td><label>No. Warta :</label></td>
                            <td><s:text id="noWarta" name="noWarta" class="normal" size="20" readonly="true"/></td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                            <%--<td>${actionBean.permohonanLaporanKawasan.noWarta}</td>--%>
                            <td><label>No. Warta :</label></td>
                            <td><s:text name="nWarta" size="20" id="nWarta" readonly="true" class="normal"/></td>
                        </c:if>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${edit}">                        
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                            <td><label><em>*</em>Tarikh Warta :</label></td>
                            <td><s:text id="datepicker" name="tarikhWarta" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                            <td><label><em>*</em>Tarikh Warta :</label></td>
                            <%--<td><s:text name="permohonanLaporanKawasan.tarikhWarta" class="datepicker" formatPattern="dd/MM/yyyy"/></td>--%>
                            <%--<td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanLaporanKawasan.tarikhWarta}"/></td>--%>
                            <td><s:text name="tWarta" id="" class="datepicker" formatPattern="dd/MM/yyyy"></s:text></td>
                        </c:if>
                    </c:if>
                    <c:if test="${view}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                            <%--<td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.TNW}"/></td>--%>
                            <%--<td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanLaporanKawasan.tarikhWarta}"/></td>--%>
                            <td><label>Tarikh Warta :</label></td>
                            <td><s:text id="datepicker" name="tarikhWarta" formatPattern="dd/MM/yyyy" readonly="true"></s:text></td>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                            <%--<td><s:text name="permohonanLaporanKawasan.tarikhWarta" formatPattern="dd/MM/yyyy"/></td>--%>
                            <%--<td><s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanLaporanKawasan.tarikhWarta}"/></td>--%>
                            <td><label>Tarikh Warta :</label></td>
                            <td><s:text name="tWarta" id="" formatPattern="dd/MM/yyyy" readonly="true"></s:text></td>
                        </c:if>                        
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">                        
                        <c:if test="${edit}">
                            <td><label><em>*</em>Tarikh Terima Warta :</label></td>
                            <%--<td><s:text name="permohonanLaporanKawasan.tarikhTerimaWarta" class="datepicker" formatPattern="dd/MM/yyyy"/></td>--%>
                            <td><s:text name="terimaWarta" id="" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                        </c:if>
                    </c:if>
                    <c:if test="${view}">
                        <td><label>Tarikh Terima Warta :</label></td>
                        <td>
                            <%--<s:text name="permohonanLaporanKawasan.tarikhTerimaWarta" formatPattern="dd/MM/yyyy"/>--%>
                            <%--<s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanLaporanKawasan.tarikhTerimaWarta}"/>--%>
                            <s:text name="terimaWarta" id="" formatPattern="dd/MM/yyyy" readonly="true"/>
                        </td>   
                    </c:if>
                </tr>         
                <tr>
                    <c:if test="${edit}">
                        <c:if test="${actionBean.stageId ne '19TrmdanImbsWrta'}">
                            <td></td><td><s:button name="sediaDraftWartaSimpan" value="Simpan" class="btn" onclick="if(val2())doSubmit(this.form, this.name, 'page_div')"/></td>
                        </c:if>
                    </c:if>
                </tr>
            </table>
        </div>
    </fieldset>
</s:form>

