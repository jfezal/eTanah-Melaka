<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>
<%@ page import="java.io.*,etanah.Configuration" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.PermohonanStrataActionBean">
    <div class="subtitle">

        <fieldset class="aras1">
            <p>
                <label>Tarikh Siasatan : </label>
                <fmt:formatDate value="${actionBean.laporanTanah.tarikhSiasat}" pattern="dd/MM/yyyy" /> &nbsp;
            </p>

            <legend>Laporan Kedudukan Tanah</legend>
            <p>
                <label>Lokasi : </label>
                ${actionBean.pemilik.laporanLokasi}&nbsp;
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
                ${actionBean.pemilik.laporanBandarTerdekat}&nbsp;
            </p>
            <c:if test="${((actionBean.pemilik.laporanUntukKediaman ne null)||(actionBean.pemilik.laporanUntukPejabat ne null) ||(actionBean.pemilik.laporanUntukPerniagaan ne null))}">
                <p> <label>Kegunaan :</label>
                </c:if>
            <table><tr><td>
                        <c:if test="${actionBean.pemilik.laporanUntukKediaman eq 'Y'}">
                            Kediaman &nbsp; <br/>
                        </c:if>

                        <c:if test="${actionBean.pemilik.laporanUntukPejabat eq 'Y'}">
                            Pejabat &nbsp; <br/>
                        </c:if>
                        <c:if test="${actionBean.pemilik.laporanUntukPerniagaan eq 'Y'}">
                            Perniagaan &nbsp; <br/> 
                        </c:if> 
                <c:if test="${actionBean.kodNegeri eq '05'}">    
                        <c:if test="${actionBean.pemilik.laporanKegunaanLain ne null}">
                            Lain-lain :  ${actionBean.pemilik.laporanKegunaanLain} &nbsp;                                 
                       </c:if>  
                </c:if>  
                        
                    
                    </td></tr></table>
            </p>

            <%--<c:if test="${actionBean.pemilik.laporanUntukPejabat eq 'Y'}">
                <p>
                    <label>&nbsp;</label> Pejabat &nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.pemilik.laporanUntukPerniagaan eq 'Y'}">
                <p>                   
                    <label>&nbsp;</label> Perniagaan &nbsp;
                </p>
            </c:if>--%>
           <c:if test="${actionBean.kodNegeri ne '05'}"> 
            <c:if test="${actionBean.pemilik.laporanKegunaanLain ne null}">
                <p>
                    <label>Jika lain kegunaan</label>
                    ${actionBean.pemilik.laporanKegunaanLain} &nbsp;
                </p>
            </c:if>
            </c:if>    
            <p>
                <label>Jenis Kos Rendah :</label>
                <c:if test="${actionBean.pemilik.laporanKosRendah eq 'Y'}">
                    Ya <br />
                    <label>No. Sijil Kos Rendah :</label>
                    ${actionBean.pemilik.cfNoSijil} &nbsp;
                </c:if>
                <c:if test="${actionBean.pemilik.laporanKosRendah eq 'N'}">
                    Tidak
                </c:if>
            </p>
            <br>
        </fieldset>

    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Keadaan Tanah
            </legend>

            <p>
                <label>Keadaan Tanah :  </label>
                ${actionBean.pemilik.laporankeadaanTanah}&nbsp;
            </p>
            <br>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Maklumat Bangunan
            </legend>

            <p>
                <label>Bilangan Blok :  </label>
                ${actionBean.pemilik.laporanBilBangunan}&nbsp;
            </p>
            <p>
                <label>Bilangan Petak :  </label>
                ${actionBean.pemilik.laporanBilPetak}&nbsp;
            </p>
            <c:if test="${actionBean.hidBlokSem}">
                <p>
                    <label>Bilangan Blok Sementara:  </label>
                    ${actionBean.pemilik.laporanBilBangunanProvisional}&nbsp;
                </p>
                <p>
                    <label>Bilangan Petak di Blok Sementara:  </label>
                    ${actionBean.pemilik.laporanBilPetakProvisional}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.hidBlokSem2}">    
            <p>
                <label>Bilangan Petak Tanah:  </label>
                ${actionBean.pemilik.laporanBilPetakTanah}&nbsp;
            </p>
            </c:if>
            <br>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <%--Kemudahan - Kemudahan--%>
                Senarai Harta Bersama
            </legend>
            <c:set var="i" value="1"/>
            <c:if test="${actionBean.pemilik.laporanKemudahan1 ne null}"><p>
                    <label>Kemudahan - Kemudahan :</label> <%--1)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan1}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan2 ne null}"><p>
                    <label>&nbsp;</label> <%--2)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan2}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan3 ne null}"><p>
                    <label>&nbsp;</label> <%--3)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan3}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan4 ne null}"> <p>
                    <label>&nbsp;</label> <%--4)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan4}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan5 ne null}"> <p>
                    <label>&nbsp;</label> <%--5)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan5}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan6 ne null}"><p>
                    <label>&nbsp;</label> <%--6)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan6}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan7 ne null}"> <p>
                    <label>&nbsp;</label> <%--7)--%> ${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan7}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan8 ne null}"> <p>
                    <label>&nbsp;</label> <%--8)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan8}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan9 ne null}"> <p>
                    <label>&nbsp;</label> <%--9)--%>${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan9}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan10 ne null}"><p>
                    <label>&nbsp;</label> <%--10)--%> ${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan10}&nbsp;
                </p>
            </c:if>   
            <c:if test="${actionBean.kodNegeri eq '05'}">   
            <c:if test="${actionBean.pemilik.laporanKemudahan11 ne null}"><p>
                    <label>&nbsp;</label> <%--12)--%> ${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan11}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan12 ne null}"><p>
                    <label>&nbsp;</label> <%--12)--%> ${i}) <c:set var="i" value="${i+1}"/>
                    Lain-lain : ${actionBean.pemilik.laporanKemudahan12}&nbsp;
                </p>
            </c:if>    
                
            </c:if>     
            <c:if test="${actionBean.kodNegeri ne '05'}">       
            <c:if test="${actionBean.pemilik.laporanKemudahan12 ne null}"><p>
                    <label>&nbsp;</label> <%--12)--%> ${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan12}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemilik.laporanKemudahan13 ne null}"><p>
                    <label>&nbsp;</label> <%--12)--%> ${i}) <c:set var="i" value="${i+1}"/>
                    ${actionBean.pemilik.laporanKemudahan13} - ${actionBean.pemilik.laporanKemudahan11}&nbsp;
                </p>
            </c:if>
           </c:if> 
            <br>
        </fieldset>
    </div>
    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>Muat naik Imej Laporan</legend><br>
    <c:set var="filePath"  value="${actionBean.imageFolderPath}" />
    <%
                String file = "C:/" + (String) pageContext.getAttribute("filePath");
                File f = new File(file);
                String[] fileNames = f.list();
                File[] fileObjects = f.listFiles();
    %>
    <table border="0" cellspacing="20" align="center">
        <c:choose>
            <c:when test="${fn:length(actionBean.senaraiKandungan) > 0}">
                <c:forEach items="${actionBean.senaraiKandungan}" var="item" varStatus="loop">
                  <!--<font color="black" size="3">${loop.count} . ${item.dokumen.kodDokumen.nama} </font><br/> -->
                    <c:if test="${loop.count mod 8 eq 0}">
                        <tr>
                        </c:if>
                        <td valign="top">
                            <!--<s:button name="close" value="Hapus Imej" onclick="hapusimej(this.name, this.form,'${item.idDokumen}');"/><br/>
                            <s:button name="close" value="Hapus Imej" onclick="hapusimej('${item.dokumen.idDokumen}');"/><br/> -->
                            <img src="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" height="100" width="105" data-plusimage="${pageContext.request.contextPath}/dokumen/view/${item.dokumen.idDokumen}" data-plussize="700,466"  />
                        </td>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                <tr>
                    <td><font color="red">Tiada Imej Imbasan.</font></td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
        </fieldset>
    </div>--%>
</s:form>