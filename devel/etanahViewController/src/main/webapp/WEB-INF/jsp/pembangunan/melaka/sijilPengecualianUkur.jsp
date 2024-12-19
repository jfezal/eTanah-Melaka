<%--
    Document   : sijilPengecualianUkur
    Created on : Jul 26, 2010, 11:10:49 AM
    Author     : Rohan
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>
<style type="text/css">
    #tdAyat {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:justify;
        width:25em;
    }
</style>
<%
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    String date_show = sdf1.format(new Date());
%>
<s:form beanclass="etanah.view.stripes.pembangunan.SijilPengecualianUkurActionBean">
    <s:messages/>
    <s:errors/>
    <%@page contentType="text/html" import="java.util.*" %>
    <%--    <s:hidden name="kandunganK.kertas.idKertas"/>--%>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend></legend>         
            <table border="0" width="100%">
                <tr><td align="center"><b><font color="#003194">SIJIL PENGECUALIAN BAYARAN UKUR</font></b></td></tr>
            </table><br>       
                <tr><td>
                        <table border="0">                           
                            <tr>
                                <td><b>Permohonan Ukur No.</b></td>
                                <td>&nbsp;:&nbsp;</td>
                                <c:if test = "${actionBean.permohonanUkur.noPermohonanUkur ne null}">
                                <td><s:hidden name="permohonanUkur.idMohonUkur"/>
                                ${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;
                                </td>
                                </c:if>
                                <c:if test = "${actionBean.permohonanUkur.noPermohonanUkur eq null}">
                                <td>-</td>
                                </c:if>
                            </tr>
                            
                            <tr>
                                <td><b>Mukim/Pekan/Bandar</b></td>
                                <td>&nbsp;:&nbsp;</td>
                                <td>${actionBean.hakmilik.bandarPekanMukim.nama} &nbsp;</td>
                            </tr>
                            
                            <tr>
                                <td><b>Daerah</b></td>
                                <td>&nbsp;:&nbsp;</td>
                                <td>${actionBean.hakmilik.daerah.nama} &nbsp;</td>
                            </tr>
                            
                            <tr>
                                <td><b>Fail Pejabat Tanah</b></td>
                                <td>&nbsp;:&nbsp;</td>
                                <td>${actionBean.permohonan.idPermohonan} &nbsp;</td>
                            </tr>
                            
                            <tr>
                                <td><b>Tujuan Kegunaan Tanah</b></td>
                                <td>&nbsp;:&nbsp;</td>
                                <td>${actionBean.hakmilik.kegunaanTanah.nama}</td>
                            </tr>

                            <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                                <c:if test="${edit}">
                                <tr>
                                    <td><b>Tarikh Perakuan</b></td>
                                    <td>&nbsp;:&nbsp;</td>
                                    <td>
                                        <s:text name="tarikhPerakuan" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" size="12"/>
                                    </td>                                   
                                </tr>
                                
                                <tr>
                                    <td><b>Fail P.T.G</b></td>
                                    <td>&nbsp;:&nbsp;</td>
                                    <td>                                    
                                        <s:text name="failPTG" id="failPTG" size="30" class="normal_text"/>
                                    </td>                                   
                                </tr>
                                </c:if>
                                <c:if test="${edit2}">
                                <tr>
                                    <td><b>Tarikh Perakuan</b></td>
                                    <td>&nbsp;:&nbsp;</td>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan ne null}">
                                    <td>
                                        <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanUkur.tarikhPerakuan}"/>                                   
                                    </td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan eq null}">
                                    <td>
                                        -                                  
                                    </td>
                                    </c:if>
                                </tr>
                                
                                <tr>
                                    <td><b>Fail P.T.G</b></td>
                                    <td>&nbsp;:&nbsp;</td>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan ne null}">
                                    <td>                                    
                                       ${actionBean.permohonanUkur.noFailISO}
                                    </td> 
                                    </c:if>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan eq null}">
                                    <td>                                    
                                       -
                                    </td> 
                                    </c:if>
                                </tr>
                                </c:if>
                            </c:if>
                            
                            <c:if test="${edit3}">
                                <tr>
                                    <td><b>Tarikh Perakuan</b></td>
                                    <td>&nbsp;:&nbsp;</td>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan ne null}">
                                    <td>
                                        <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonanUkur.tarikhPerakuan}"/>                                   
                                    </td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan eq null}">
                                    <td>
                                        -                                  
                                    </td>
                                    </c:if>
                                </tr>
                                
                                <tr>
                                    <td><b>Fail P.T.G</b></td>
                                    <td>&nbsp;:&nbsp;</td>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan ne null}">
                                    <td>                                    
                                       ${actionBean.permohonanUkur.noFailISO}
                                    </td> 
                                    </c:if>
                                    <c:if test="${actionBean.permohonanUkur.tarikhPerakuan eq null}">
                                    <td>                                    
                                       -
                                    </td> 
                                    </c:if>
                                </tr>
                                </c:if>
                    </td>
                </tr>
            </table>
            <br><br>
            <table border="0" width="100%" >
                <tr>
                    <td>
                        <table border="0" width="100%" align="center">
                            <tr>
                                <td><b><font color="black">Pada menjalankan kuasa-kuasa yang diberi kepada saya oleh Seksyen 4(2) Perintah Kanun Tanah Negara (Bayaran Ukur)1965, saya dengan ini mengesahkan bahawa permohonan ukur tersebut di atas adalah untuk tujuan awam, maka dengan ini dikecualikan daripada semua bayaran ukur di bawah perintah ini.</font></b></td>
                            </tr>

                        </table>
                    </td>
                </tr>               
                <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                    <c:if test="${edit}">
                    <tr>
                        <td align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td>
                    </tr>
                    </c:if>
                </c:if>
            </table>
        </fieldset>
    </div>
</s:form>