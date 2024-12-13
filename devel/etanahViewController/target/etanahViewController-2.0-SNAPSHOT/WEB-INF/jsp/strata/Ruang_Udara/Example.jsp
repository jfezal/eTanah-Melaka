<%--
    Document   : Example
    Created on : Jul 1, 2010, 11:25:51 AM
    Author     : User
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.ExampleActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Skrin Kemasukan Maklumat permohonan</legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                
                    <tr><td colspan="3" bgcolor="orange"><b>Maklumat JuruUkur</b></td> </tr><br>

                    <tr>
                        <td class="label">First name:</td>
                           <td class="value"></td>
                    </tr>
                    <tr>
                        <td class="label">Last name:</td>
                        <td class="value"></td>
                    </tr>
                    <tr>
                        <td class="label">Email:</td>
                        <td class="value"></td>
                    </tr>
                    <tr>
                    <td class="label">Phone number:</td>
                    <td class="value"></td>
                    </tr>
                    <tr>
                      <td>Jenis Bangunan</td>
                        <td>
                            <select name="jenisBangunan" style="width:200px">
                                <option value="">Sreenu</option>
                                <option value="">Reddy</option>
                                <option value="">Sai</option>
                                <option value="">Suman</option>
                                <option value="">Madhu</option>

                            </select>
                       </td>
                      </tr>
                    <tr>
                        <td width="25"><b>Tarikh Siasatan :</b></td>
                    <td>&nbsp;<s:text name="tarikhSiasatan" id="datepicker" class="datepicker" size="12" /></td>
                </tr>
                <tr> <label>&nbsp;</label>
                        <s:submit name="search" value="Cari" class="btn"/>
                </tr>
               </table>
            </div>
        </fieldset>
    </div>
  </s:form>