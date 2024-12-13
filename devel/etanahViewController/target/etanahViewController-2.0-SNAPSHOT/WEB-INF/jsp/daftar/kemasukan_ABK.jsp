<%-- 
    Document   : kemasukan_ABK
    Created on : 05 Oktober 2009, 9:51:27 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt"%>

<c-rt:set var="now2" value="<%=new java.util.Date()%>"/>

<table width="100%" bgcolor="#00FFFF">
    <tr>
        <td width="50%" ><div style="float:left;" class="formtitle">AWALAN DI BELAKANG KAUNTER</div></td>
        <td width="50%" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>
<form:form beanclass="etanah.view.stripes.fail">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Perserahan Dokumen
            </legend>
            <div class="content" align="center">
                <table>

                    <tr>
                        <td class="rowlabel1">Nama Pemohon : </td>
                        <td class="input1">
                            <input type="text" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Alamat : </td>
                        <td class="input1">
                            <input type="text" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Poskod : </td>
                        <td class="input1">
                            <input type="text" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Bandar : </td>
                        <td class="input1">
                            <input type="text" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Negeri : </td>
                        <td class="input1">
                            <s:select name="">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:option value="">Negeri Sembilan</s:option>
                                <s:option value="">Melaka</s:option>
                            </s:select>
                        </td>
                    </tr>

                </table>
                
            </div>
        </fieldset>

    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Kod Pejabat :</td>
                        <td class="input1">
                           PT
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Urusan :</td>
                        <td class="input1">
                          <s:select name="">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:option value="">Urusan 1</s:option>
                                <s:option value="">Urusan 2</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh & Masa Perserahan : </td>
                        <td class="input1">
                            <fmt:formatDate type="time" pattern="dd/MM/yyyy , h:mm " timeZone="GMT+8" value="${now2}"/>

                        <script type="text/javascript">
                            var clockTime = getClockTime();document.write(clockTime);
                        </script>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Jumlah Hakmilik Terlibat : </td>
                        <td class="input1">
                            <s:text name="" value=""/>
                        </td>
                    </tr>

                     <tr>
                        <td class="rowlabel1">No Hakmilik Bersiri : </td>
                        <td class="input1">
                            <s:select name="">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:option value="">1</s:option>
                                <s:option value="">2</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Negeri: </td>
                        <td class="input1">
                            07
                        </td>
                    </tr>

                     <tr>
                        <td class="rowlabel1">Daerah : </td>
                        <td class="input1">
                            <s:select name="">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:option value="">1</s:option>
                                <s:option value="">2</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Bandar / Pekan / Mukim : </td>
                        <td class="input1">
                            <s:select name="">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:option value="">1</s:option>
                                <s:option value="">2</s:option>
                            </s:select>
                        </td>
                    </tr>
                     <tr>
                        <td class="rowlabel1">Jenis Hakmilik : </td>
                        <td class="input1">
                            <s:select name="">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:option value="">1</s:option>
                                <s:option value="">2</s:option>
                            </s:select>
                        </td>
                    </tr>

                     <tr>
                        <td class="rowlabel1">No Hakmilik Dari : </td>
                        <td class="input1">
                            <s:text name="" value=""/> Hingga <s:text name="" value=""/>
                        </td>
                    </tr>


                </table>

            </div>
        </fieldset>

    </div>
     <br>
     <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik
            </legend>
            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th>Bil</th><th>ID Hakmilik</th>
                    </tr>
                    </th>
                    <tr>
                        <td>1</td><td>0704030201100006</td>
                    </tr>
                </table>

            </div>
        </fieldset>

    </div>
     <br>
    <table width="100%" bgcolor="#00FFFF">
        <tr>
            <td width="80%" height="20"></td>
            <td width="20%" height="20">
                
                <s:submit name="searchAllPermohonan" value="Daftar" class="btn"/>
                <s:submit name="searchAllPermohonan" value="Isi Semula" class="btn"/>
                <s:submit name="searchAllPermohonan" value="Keluar" class="btn"/>

            </td>
        </tr>
    </table>
</form:form>