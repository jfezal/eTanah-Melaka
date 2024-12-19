<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<table width="100%" bgcolor="#00FFFF">
                <tr>
                    <td width="50%" ><div style="float:left;" class="formtitle">Maklumat Urusniaga</div></td>
                    <td width="50%" ><div style="float:right;" class="formtitle">REGMAU07</div></td>
                </tr>
 </table>
 <form:form beanclass="etanah.view.stripes.fail">
    <ul class="tabs">
        <li><a href="#tabs-1">Maklumat Urusniaga</a></li>
        <li><a href="#tabs-2">Status</a></li>
        <li><a href="#tabs-3">Fail</a></li>
    </ul>

<div class="tab_container">
    <div id="tabs-1" class="tab_content">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Fail
                </legend>
                <div class="content" align="center">
                    <table>
                       
                            <tr>
                                <td class="rowlabel1">No Fail :</td>
                                <td class="input1"><s:text name=""/> </td>
                            </tr>
                            <tr>
                                <td class="rowlabel1">No Mahkamah :</td>
                                <td class="input1"><s:text name=""/> </td>
                            </tr>
                            <tr>
                                <td class="rowlabel1">Tarikh Mahkamah :</td>
                                <td class="input1"><s:text name="" id="datepicker" class="datepicker"/> </td>
                            </tr>
                            <tr>
                                <td class="rowlabel1">Nama Mahkamah :</td>
                                <td class="input1">
                                    <s:select name="">
                                        <s:option value="">-- Sila Pilih --</s:option>
                                        <s:option value="">Mahkamah 1</s:option>
                                        <s:option value="">Mahkamah 2</s:option>
                                        <s:option value="">Mahkamah 3</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td></td>

                            </tr>
                            <tr>
                                <td></td>
                                <td>


                                   

                                </td>
                            </tr>
                        
                    </table>
                </div>
            </fieldset>
        </div>
    </div>
    <div id="tabs-2" class="tab_content">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Fail
                </legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td class="rowlabel1">Status :</td>
                            <td class="input1">Hantar</td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </div>
    <div id="tabs-3" class="tab_content">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Fail
                </legend>
                <div class="content" align="center">
                    <display:table name="" pagesize="10">
                     <display:setProperty name="basic.msg.empty_list" value="Tiada Data"/>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </div>

</div>
<br>
<br>
<table width="100%" bgcolor="#00FFFF">
                <tr>
                    <td width="90%"></td>
                    <td width="10%">
                        
                        <s:submit name="searchAllPermohonan" value="Terus" class="buttonterus"/>
                        <s:submit name="searchAllPermohonan" value="Keluar" id="buttonkeluar"/>
                    </td>
                </tr>
 </table>
</form:form>



