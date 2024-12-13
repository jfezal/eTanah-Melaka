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
        width:20em;
    }

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

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
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
            <%--<div class="content"  align="center">--%>
            <table border="0" width="100%">
                <tr><td align="center"><b><font color="#003194">SIJIL PEMBEBASAN UPAH UKUR</font></b></td></tr>
            </table><br>
            <table border="0" width="100%" ><s:hidden name="permohonanUkur.idMohonUkur" />
                <%--<tr><td>
                        <table border="0" width="100%" align="center" cellspacing="5">
                        <tr>
                            <td id="tdLabel"><b>ID Permohonan :</b></td>
                            <td id="tdDisplay">${actionBean.permohonan.idPermohonan} &nbsp;</td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Permohonan Ukur No. : </b></td>
                            
                            <td id="tdDisplay">${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;</td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Mukim/Pekan/Bandar : </b></td>
                            <td id="tdDisplay">${actionBean.hakmilik.bandarPekanMukim.nama} &nbsp;</td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Daerah : </b></td>
                            <td id="tdDisplay">${actionBean.hakmilik.daerah.nama} &nbsp;</td>
                        <tr>
                            <td id="tdLabel"><b>Fail Pejabat Tanah : </b></td>
                            <td id="tdDisplay">${actionBean.permohonan.idPermohonan} &nbsp;</td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Tujuan Kegunaan Tanah : </b></td>
                            <td id="tdDisplay">${actionBean.hakmilik.kegunaanTanah.nama}</td>
                        </tr>

                        <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <tr>
                            <td id="tdLabel"><b>Tarikh Perakuan : </b></td>
                            <td id="tdDisplay">
                                  <s:text name="tarikhPerakuan" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" size="12"/>
                            </td>
                            <td>${actionBean.permohonanUkur.tarikhPerakuan}</td>
                        </tr>
                        <tr>
                            <td id="tdLabel"><b>Fail P.T.G : </b></td>
                            <td id="tdDisplay">
                                <s:text name="failPTG" id="failPTG" size="30" class="normal_text"/>
                            </td>
                            <td>${actionBean.permohonanUkur.tarikhPerakuan}</td>
                        </tr>
                      </c:if>

                        </table>
                    </td>
                </tr>--%>
                <tr>
                    <td>

                        <table border="0" width="100%" align="center">
                            <tr>
                                <td style="alignment-adjust: central">
                                    
                                </td>
                            </tr>
                            <tr>
                                <%--<td id="tdAyat"><b><font color="black">Pada menjalankan kuasa-kuasa yang diberi kepada saya oleh Seksyen 4(2) Perintah Kanun Tanah Negara (Bayaran Ukur)1965, saya dengan ini mengesahkan bahawa permohonan ukur tersebut di atas adalah untuk tujuan awam, maka dengan ini dikecualikan daripada semua bayaran ukur di bawah perintah ini.</font></b></td>--%>
                                <%--<td align="center"><b> <s:textarea name="tujuan" rows="3" cols="90" class="normal_text" style="resize: none;border-style: none;" value="Mengikut kuasa yang diberikan di bawah perenggan 4(2) N.L.C. (Survey Fees) Order 1965 yang telah diwartakan mengikut L.N. 486 bertarikh 30.12.1965, pembebasan upah ukur adalah diberi bagi tujuan pengambilan sebahagian tanah ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik}, ${actionBean.hakmilik.bandarPekanMukim.nama}, ${actionBean.hakmilik.daerah.nama} ${actionBean.permohonan.sebab}"/></b></td>--%>
                                <td align="center"><b> <s:textarea name="tujuan" rows="3" cols="90" class="normal_text" style="resize: none;border-style: none" value="${actionBean.tujuan}"/></b></td>
                            </tr>

                        </table>
                        <br>
                    </td>
                </tr>
                <%--<tr>
                    <td>
                        <table border="0" width="96%">
                            <tr>
                                <td id="tdLabel"><b>Tarikh Perakuan : </b><s:text name="permohonanUkur.tarikhPerakuan" id="datepicker" class="datepicker" size="12"/></td>
                                <td>${actionBean.permohonanUkur.tarikhPerakuan}</td>
                            </tr>
                        </table>
                    </td>
                </tr>--%>





                <c:choose>
                    <c:when test="${actionBean.pengguna.kodCawangan.kod eq '00' && actionBean.permohonanUkur.noPermohonanUkur ne null}">
                        <tr>
                            <td align="center">
                                <%--  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${actionBean.pengguna.kodCawangan.kod eq '00' && actionBean.permohonanUkur.noPermohonanUkur eq null}">
                        <tr>
                            <td align="center">
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${actionBean.pengguna.kodCawangan.kod eq '05' && actionBean.permohonanUkur.noPermohonanUkur eq null}">
                        <tr>
                            <td align="center">
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </td>
                        </tr>
                    </c:when>

                    <%--  <c:otherwise>
                          <tr>
                              <td align="center">
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                              </td>
                          </tr>
                      </c:otherwise>--%>
                </c:choose>
            </table>
            <%--<p>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>--%>

        </fieldset>
    </div>
</s:form>