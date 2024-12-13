<%-- 
    Document   : endorsan_Kelulusan
    Created on : Jul 15, 2010, 9:38:04 AM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>


<s:form beanclass="etanah.view.stripes.pembangunan.EndorsanKelulusanActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Endorsan</legend>
            <div class="content" align="center">
                <table border="0" width="80%">                        
                    <c:if test="${edit}">
                        <tr><td align="center">
                                <table width="80%" align="center"  cellspacing="10" class="tablecloth">
                                    <tr>
                                        <td align="center" width="5%"><s:checkbox name="pelanTatur"/> </td>
                                        <td> Pelan Prahitung </td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="pelanPrahitung"/> </td>
                                        <td> Pelan Tatatur </td>
                                    </tr>                          
                                  </table>                     
                            </td></tr><br>
                        <tr><td align="center">
                         <s:button name="endorsan" id="endorsan" value=" Endorsan " class="btn"/>
                            </td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center">
                                <table width="80%" align="center"  cellspacing="10" class="tablecloth">
                                    <tr>
                                        <td><s:checkbox name="pelanTatur"/> </td>
                                        <td> Pelan Prahitung </td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="pelanPrahitung"/> </td>
                                        <td> Pelan Tatatur </td>
                                    </tr>
                                  </table>
                            </td></tr><br>
                    </c:if>
                   </table>
            </div>
        </fieldset>
    </div>
    <%--<br><br>--%>
    <%--<hr/><br>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Kelulusan</legend>
            <div class="content" align="center">
                <table border="0" width="80%">                    
                      <c:if test="${edit}">
                        <tr><td align="center">
                                <table width="80%" align="center"  cellspacing="10" class="tablecloth">
                                    <tr>
                                        <td width="5%" align="center"><s:checkbox name="borang"/> </td>
                                        <td> Borang 9A </td>
                                    </tr>
                                    <tr>
                                        <td width="5%" align="center"><s:checkbox name="borang"/> </td>
                                        <td> Borang 9B </td>
                                    </tr>
                                     <tr>
                                        <td width="5%" align="center"><s:checkbox name="borang"/> </td>
                                        <td> Borang 9C </td>
                                    </tr>
                                    <tr>
                                        <td width="5%" align="center"><s:checkbox name="borang"/> </td>
                                        <td> Borang 12D </td>
                                    </tr>
                                    <tr>
                                        <td width="5%" align="center"><s:checkbox name="borang"/> </td>
                                        <td> Borang 7D </td>
                                    </tr>
                                  </table>
                            </td></tr><br>
                        <tr><td align="center">
                            <s:button name="kelulusan" id="kelulusan" value=" Kelulusan " class="btn"/>
                            </td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td align="center">
                                <table width="80%" align="center"  cellspacing="10" class="tablecloth">
                                    <tr>
                                        <td><s:checkbox name="borang"/> </td>
                                        <td> Borang 9A </td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="borang"/> </td>
                                        <td> Borang 9B </td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="borang"/> </td>
                                        <td> Borang 9C </td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="borang"/> </td>
                                        <td> Borang 12D </td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="borang"/> </td>
                                        <td> Borang 7D </td>
                                    </tr>
                                  </table>
                            </td></tr><br>
                    </c:if>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>