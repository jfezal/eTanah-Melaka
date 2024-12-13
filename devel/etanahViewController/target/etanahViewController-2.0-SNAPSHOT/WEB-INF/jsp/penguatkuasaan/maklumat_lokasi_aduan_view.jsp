<%-- 
    Document   : maklumat_lokasi_aduan
    Created on : Jan 15, 2010, 11:44:55 AM
    Author     : nurshahida.radzi
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLokasiActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <%--<c:if  test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod eq '429' || actionBean.permohonan.kodUrusan.kod eq '351'|| actionBean.permohonan.kodUrusan.kod eq '352'}">--%>
            <c:if  test="${actionBean.kodNegeri eq '05'}">
                <legend>
                    Lokasi Aduan
                </legend>
            </c:if>
            <%--      <c:if  test="${actionBean.kodNegeri eq '05' && actionBean.permohonan.kodUrusan.kod ne '429' || actionBean.permohonan.kodUrusan.kod ne '351'|| actionBean.permohonan.kodUrusan.kod ne '352'}">
                      <legend>
                              Lokasi Kejadian
                      </legend>
                  </c:if>--%>
            <c:if  test="${actionBean.kodNegeri eq '04'}">
                <legend>
                    Lokasi Kejadian
                </legend>
            </c:if>

            <div class="content">

                <p>
                    <label>Lokasi Aduan :</label>
                    ${actionBean.aduanLokasi.cawangan.name}&nbsp;
                </p>

                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    ${actionBean.aduanLokasi.bandarPekanMukim.nama}&nbsp;
                </p>

                <p>
                    <label>Jenis Tanah :</label>
                    ${actionBean.aduanLokasi.pemilikan.nama}&nbsp;
                </p>

                <p>
                    <label>Nombor Lot :</label>
                    ${actionBean.aduanLokasi.noLot}&nbsp;
                </p>

                <p>
                    <label>Lokasi :</label>
                    ${actionBean.aduanLokasi.lokasi}&nbsp;
                </p>


            </div>
        </fieldset>
    </div>
</s:form>