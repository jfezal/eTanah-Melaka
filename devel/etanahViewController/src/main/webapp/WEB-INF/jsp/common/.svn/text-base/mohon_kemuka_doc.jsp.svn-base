<%--
    Document   : mohon_kemuka_doc.jsp
    Created on : Aug 16, 2012, 12:15:38 PM
    Author     : Aizuddin Orogenic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>
        <s:useActionBean beanclass="etanah.view.stripes.common.MohonKemukaDocActionBean" var="penggunaperanan"/>
        <s:form beanclass="etanah.view.stripes.common.MohonKemukaDocActionBean">
            <s:messages />
            <s:errors />
            <table width="95%" border="0">
                <c:if test="${
                      actionBean.permohonan.kodUrusan.kod eq 'IRM'
                          or actionBean.permohonan.kodUrusan.kod eq 'IPM'}">
                      <tr>
                          <th colspan="6" scope="col">Borang 2B</th>
                      </tr>
                      <tr>
                          <td colspan="6">  <div align="center">(Seksyen 15)</div></td>
                      </tr>
                      <tr>
                          <td colspan="6"> <div align="center">Kanun Tanah Negara</div></td>
                      </tr>
                      <tr>
                          <td colspan="6">&nbsp;</td>
                      </tr>
                      <tr>
                          <td colspan="6"><div align="center">NOTIS SUPAYA MENGEMUKAKAN DOKUMEN</div></td>
                      </tr>
                      <tr>
                          <td colspan="6">&nbsp;</td>
                      </tr>
                      <!--                <tr>
                                          <td width="27%">&nbsp;</td>
                                          <td colspan="2">Pihak Yang Terlibat</td>
                                          <td width="1%"><strong>:</strong></td>
                                          <td width="23%"><label name="pihaknama" id="pihaknama" value="pihaknama" /></td>
                                          <td width="27%">&nbsp;</td>
                      
                                      </tr>-->
                      <tr>
                          <td width="29%">&nbsp;</td>
                          <td colspan="2">Dokumen Yang Perlu Diserahkan</td>
                          <td width="1%"><strong>:</strong></td>
                          <td width="38%"><s:textarea name="catatanBorang2B" id="catatanBorang2B" rows="3"cols="50" value="catatanBorang2B" /></td>
                          <td width="12%">&nbsp;</td>
                      </tr>
                </c:if>    
                <c:if test="${
                      actionBean.permohonan.kodUrusan.kod eq 'IRTB'
                          or actionBean.permohonan.kodUrusan.kod eq 'IRTBB'
                          or actionBean.permohonan.kodUrusan.kod eq 'TTWB'
                          or actionBean.permohonan.kodUrusan.kod eq 'JPB'
                          or actionBean.permohonan.kodUrusan.kod eq 'JMB'
                          or actionBean.permohonan.kodUrusan.kod eq 'IDMLB'
                          or actionBean.permohonan.kodUrusan.kod eq 'TTWKP'
                          or actionBean.permohonan.kodUrusan.kod eq 'TTWLB'
                          or actionBean.permohonan.kodUrusan.kod eq 'TTWLM'
                           or actionBean.permohonan.kodUrusan.kod eq 'IDMLB'
                          or actionBean.permohonan.kodUrusan.kod eq 'KRM'
                          or actionBean.permohonan.kodUrusan.kod eq 'KRMB'}">
                      <tr>
                          <th colspan="6" scope="col">Surat Mohon Kemukakan DHKK</th>
                      </tr>
                      <tr>
                          <td colspan="6">&nbsp;</td>
                      </tr>
                      <tr>
                          <td colspan="6"><div align="center">Mohon Kemukakan Dokumen Hakmilik</div></td>
                      </tr>
                      <tr>
                          <td colspan="6">&nbsp;</td>
                      </tr>
                      <tr>
                          <td width="29%">&nbsp;</td>
                          <td colspan="2">Ulasan</td>
                          <td width="1%"><strong>:</strong></td>
                          <td width="38%"><s:textarea name="ulasan" id="ulasan" rows="3"cols="50" value="ulasan" /></td>
                          <td width="12%">&nbsp;</td>
                      </tr>
                </c:if>
                <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">Tempoh Serah Dokumen</td>
                    <td><strong>:</strong></td>
                    <td><s:text name="tempohHari" id="tempohHari" size="3" value="tempohHari" /> hari</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="6">
                        <div align="center">
                            <s:button name="simpanTempoh" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </div>
                    </td>
                </tr>
            </table>
        </s:form>
        <p>&nbsp;</p>
    </body>
</html>