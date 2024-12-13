<%-- 
    Document   : laporan_MMK
    Created on : Mar 8, 2010, 3:47:45 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--Laporan Siasatan--%>
<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
     <c:if test="${form}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kertas MMKN
            </legend>

            <div class="content" align="left">
            <table align="left">
                <tr>
                    <td class="rowlabel1" valign="top">Tajuk :</td>
                    <td class="input1"><s:textarea name="tajuk" cols="40" rows="3"/></td>
                </tr>
                <tr>
                    <td class="rowlabel1">Risalat MMKN :</td>
                    <td class="input1">MMKN</td>
                </tr>
                <tr><td class="rowlabel1">No.Ruj PTG :</td>
                    <td class="input1"><s:text name="noRujukan" size="40" /></td>
                </tr>
            </table>
             </div>
        </fieldset>
        <fieldset class="aras2">
            <legend>
                Ringkasan Permohonan
            </legend>
            <div class="content" align="left">
                <table align="left">
                    <tr>
                    <td class="rowlabel1">Pemohon :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Perihal Tanah :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Lot/PT :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Mukim :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Lokasi :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1">Kegunaan Tanah :</td>
                    <td class="input1"></td>
                </tr>
                </table>                
            </div>
        </fieldset>
        <fieldset class="aras1">
            
             <div class="content" align="left">
            <table align="left">            
                    <tr>
                        <td class="input1"><b><font size="3">1. TUJUAN</font> </b></td>
                    </tr>
                    <tr>
                        <td class="input1" valign="top">1.1 </td>                       
                    </tr>
                    <tr>
                        <td class="input1"><s:textarea name="tujuan" cols="100" rows="5"/></td>
                    </tr>
                    <tr>                       
                       <td> <s:button  name="reset" value="tambah" class="btn" onclick="return test();"/></td>
                    </tr>
                    </table>
                    <table>
                    <tr>
                    <td class="input1"><b><font size="3">2. LATAR BELAKANG </font></b></td>
                    </tr>
                    <tr>
                        <td class="input1">2.1 </td>
                    </tr>
                    <tr>
                       <td class="input1"><s:textarea name="tujuan" cols="100" rows="5"/></td>                       
                    </tr>
                    <tr>
                       <td> <s:button  name="reset" value="tambah" class="btn" onclick="return test();"/></td>
                    </tr>
                    </table>
                     <table>
                    <tr>
                        <td class="input1"><b><font size="3">3. PERAKUAN PENGARAH TANAH DAN GALIAN</font> </b></td>
                    </tr>
                    <tr>
                        <td class="input1">3.1 </td>
                    </tr>
                    <tr>
                       <td class="input1"><s:textarea name="tujuan" cols="100" rows="5"/></td>
                     </tr>
                     <tr>
                       <td> <s:button  name="reset" value="tambah" class="btn" onclick="return test();"/></td>
                    </tr>
                </table>
             </div>

        </fieldset>
        <p align="right">
            <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="" value="Simpan"/>
        </p>
    </div>
     </c:if>
    <c:if test="${view}">
        <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kertas MMKN
            </legend>
            <div class="content" align="left">
            <table align="left">
                <tr>
                    <td class="rowlabel1" valign="top">Tajuk :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1">Risalat MMKN :</td>
                    <td class="input1">MMKN</td>
                </tr>
                <tr><td class="rowlabel1">No.Ruj PTG :</td>
                    <td class="input1"></td>
                </tr>
            </table>
             </div>
        </fieldset>
        <fieldset class="aras2">
            <legend>
                Ringkasan Permohonan
            </legend>
            <div class="content" align="left">
                <table align="left">
                    <tr>
                    <td class="rowlabel1">Pemohon :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Perihal Tanah :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Lot/PT :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Mukim :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1"> Lokasi :</td>
                    <td class="input1"></td>
                </tr>
                <tr>
                    <td class="rowlabel1">Kegunaan Tanah :</td>
                    <td class="input1"></td>
                </tr>

                </table>                
            </div>
        </fieldset>
        <fieldset class="aras1">
            
             <div class="content" align="left">
            <table align="left">            
                    <tr>
                        <td class="input1"><b><font size="3">1. TUJUAN</font> </b></td>
                    </tr>
                    <tr>
                        <td class="input1" valign="top">1.1 </td>                       
                    </tr>
                    <tr>
                        <td class="input1"></td>
                    </tr>
                    <tr>                       
                       <td> </td>
                    </tr>
                    </table>
                    <table>
                    <tr>
                    <td class="input1"><b><font size="3">2. LATAR BELAKANG </font></b></td>
                    </tr>
                    <tr>
                        <td class="input1">2.1 </td>
                    </tr>
                    <tr>
                       <td class="input1"></td>                       
                    </tr>
                    <tr>
                       <td> </td>
                    </tr>
                    </table>
                     <table>
                    <tr>
                        <td class="input1"><b><font size="3">3. PERAKUAN PENGARAH TANAH DAN GALIAN</font> </b></td>
                    </tr>
                    <tr>
                        <td class="input1">3.1 </td>
                    </tr>
                    <tr>
                       <td class="input1"></td>
                     </tr>
                     <tr>
                       <td> </td>
                    </tr>
                </table>
             </div>
        </fieldset>        
    </div>
    </c:if>
</s:form>
