Serverless-IndexFS
===============

A ported IndexFS for serverless metadata management and caching. We decouple metadata caching and LevelDB backend by splitting them into two different server programs and wrapping function calls between them as Thrift RPC calls. In the ported version, metadata caching server acts like a client of LevelDB backend and they work collaborately to provide IndexFS metadata service.

To see source code of original IndexFS (SC'14): https://github.com/zhengqmark/indexfs-0.4

## YourKit

![YourKit](https://www.yourkit.com/images/yklogo.png)

YourKit supports open source projects with innovative and intelligent tools
for monitoring and profiling Java and .NET applications.
YourKit is the creator of <a href="https://secure-web.cisco.com/1W2R4YLY2hhGpl65mIlXRE6wo_Bgus3uAHj6kgi6Z1DflVl54XMtQ0_bG4gLyZxrKU1-JaPNp55Q2cQ3ftsKFpnUVDjPE4JGtM4ywlm950byJZCYI-1jgkH92zAIuQCFsDx005KZuETXhdtfIGZqSeQQ3ud1VvgxrlKrtMHbaUr37cdBphqhQeKaE8RxghLsfkxA36zftxus_N3L2xC5TDBkLd-MX8-52U5z1TQvszvleyF7LmsiQvsBqtLJi7VHFDke5NXhRgNcK-CEOltbSOapNonAY_rmdFaIBClzSk3rx561RISOnplyO8DJCbCJcb5VofGSz3w0bk2KkQpj82LYGGWx7gNJRSzVAsOvRb_lA7C1EoNYtlGgK9L061xx9T2HYE6BBDJgx7_QPEzpWqtEqXIQCknqKVaxcVewF06NYAYP8jgAlt977GYVwO0tAP8yR1Bvi0pcPFAYTu9fL8w/https%3A%2F%2Fwww.yourkit.com%2Fjava%2Fprofiler%2F">YourKit Java Profiler</a>,
<a href="https://secure-web.cisco.com/1piGOD1cy48OKLn1ALIiFbXeHFmGSfSNVVDqcuWnvPA2hUN3Z_Lv3HNh2ZhN_6Ji_c2oZo2sYQyBGroEsg--fCuHc-xSESSng1GpR9kkByOi0CLtj5i2NZFEEBEG6cTiultTSPxLP0Rambt3Y6CWrvS0EGIGkmYjrFXsuoT9HOxHok5bgMa8WkgtqBvECPAys02VWYQqTcu_JKRqsWIPIHIwRLhU6tlr-4DkpkLg5JSvVP0_FlyNCO0M0DFOiODn3T05GW3I28z8Jnlu3Ld6_-jawjTn-g3WY7B4br54NVXbNByKClKb57x8rqnPCxrvnpl5fc8piIvSjEArvu0gi26nZS2FBntop2HbLKgMpiBzFz5hFw3aK7Tn1DJC6fMtx1o3HGEM7QVM9wDTEblweqaB52HZUEZ4F1EvMJ7bUnzZzUMyUwN_pL_eAnglXrImic0HdcAIKNvGX1UVpG3XrWg/https%3A%2F%2Fwww.yourkit.com%2F.net%2Fprofiler%2F">YourKit .NET Profiler</a>,
and <a href="https://secure-web.cisco.com/1ujOsbmH037MKBTOxPNGDQ-UEwf1lyUZU_T8luSrnfjvlZ3dmoy52_vMceUkE0-aq7MEmqV-hQ6LyNLKOSfcz5R3vBy_2QsfcVzpzRDD7DQVlRseIAPvD2ChO9bAWRJXp37AFTiBdd1TrHwyexDCIkZp2tCk-AkILPmgMf_scfI0KEBw1qSsbM3ygYKQNgQCwDPIIiwyjs-534HqX7J-kGM3WB7tkWyhL8M9zPwATNoaGj4YjZ4h87bHqZM9rbPmUX-OFaf1LR8ORVTTICc6ZtF61D5RasbUElZHpCaJDPDH2QW1f609IchHvsL6XmB7hOsGmVZbWCZICM9saYYalQK5drI80YmB2e0vr_SiGUh5HW3YOxHJceECxIW9OoL7xEID4HzLW5GLpa0aEB6fWvfTE8rASBFbcAVD-Hij3fn8_7Eqnf4uBsO5qtU6OOdlTgKZQeClRKY3qdogYEB2oeQ/https%3A%2F%2Fwww.yourkit.com%2Fyoumonitor%2F">YourKit YouMonitor</a>.
