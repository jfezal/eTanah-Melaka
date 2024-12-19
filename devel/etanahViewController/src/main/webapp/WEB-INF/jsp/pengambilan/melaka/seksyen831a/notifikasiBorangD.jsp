<%-- 
    Document   : notifikasiBorangB
    Created on : 05-Aug-2010, 11:10:11
    Author     : nordiyana
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<s:form beanclass="etanah.view.stripes.pengambilan.notifikasin9ActionBean">
<div class="subtitle">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                      Borang D
                    </legend>
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                                <td align="left">Sila cetak Borang D yang terdapat didalam Senarai Dokumen di dalam tab "Dokumen" </td>

                            </tr>

                        </table>
                         <c:if test="${edit}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                               <td align="left">Sila semak semula Borang D untuk menyediakan Warta Borang D di dalam Tab 'Dokumen.
                               </td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                    <c:if test="${view}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                                <td align="left">Sila semak Borang D di dalam Tab 'Dokumen.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                    <c:if test="${form}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                                <td align="left">Sila semak Borang A telah dijana di dalam Tab 'Dokumen'.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                    <c:if test="${form1}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr>
                            <br>
                            <tr>
                                <td align="left">Senarai Dokumen.</td>
                            </tr>
                            <tr>
                                <td align="left"><table align="left"  width="100%">
                                                 <tr><td>1.</td><td>Keputusan MMKN</td>
                                                 </tr>
                                                 <tr><td>2.</td><td>Salinan Warta Borang D</td>
                                                 </tr>
                                                 <tr><td>3.</td><td>Salinan Borang D</td>
                                                 </tr>
                                                 <tr><td>4.</td><td>Salinan Borang C</td>
                                                 </tr></table></td>

                            </tr>

                        </table>
                    </div>
                    </c:if>
                     <c:if test="${form2}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                            <td align="left">Sila sediakan Surat Arah Bayaran.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                     <c:if test="${form3}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                            <td align="left">Sila sediakan Surat Makluman Keputusan MMK.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                     <c:if test="${form10}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                            <td align="left">Sila cetak Surat Penandaan Atas Tanah yang telah dijana didalam Tab 'Dokumen'.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                    <c:if test="${form11}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                            <td align="left">Sila jana Borang I dengan menekan Butang Jana Dokumen.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                    <c:if test="${form12}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                            <td align="left">Sila semak Borang I yang telah dijana didalam Tab Dokumen.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                    <c:if test="${form13}">
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left">Notifikasi</td>

                            </tr
                            <br>
                            <tr>
                            <td align="left">Sila imbas Borang I dengan menekan ikon pengimbas didalam Tab Dokumen.</td>
                            </tr>

                        </table>
                    </div>
                    </c:if>
                    </div>

                </fieldset>
            </div>
                    </div>
</s:form>

